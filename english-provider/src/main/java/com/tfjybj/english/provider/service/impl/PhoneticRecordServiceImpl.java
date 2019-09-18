package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.google.common.collect.Maps;
import com.tfjybj.english.entity.PhoneticWordEntity;
import com.tfjybj.english.entity.WordPhoneticEntity;
import com.tfjybj.english.model.PhoneticCheckModel;
import com.tfjybj.english.provider.dao.PhoneticWordDao;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import com.tfjybj.english.entity.PhoneticRecordEntity;
import com.tfjybj.english.provider.dao.PhoneticRecordDao;
import com.tfjybj.english.provider.service.PhoneticRecordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PhoneticRecordService接口实现类
 * ${base}表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Service("phoneticRecordService")
public class PhoneticRecordServiceImpl extends BaseServicePlusImpl<PhoneticRecordDao,PhoneticRecordEntity> implements PhoneticRecordService {
	
	//region 模板生成
    @Resource
    private PhoneticRecordDao phoneticRecordDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
	
	 @Autowired
     RedisUtil redisUtil;

    @Autowired
    PhoneticWordDao phoneticWordDao;

    @Autowired
    RedisToDbService redisToDbService;

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * @param
     * @return 进入音标检测
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    @Override
    public PhoneticCheckModel findPhoneticCheck() {
        String userId =UserUtil.getCurrentUser().getUserId();
        boolean flag = redisUtil.hasKey(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.Done);
        if (flag) {
            redisToDbService.PhoneticToDoToDB(userId, RecordDate.Date());
        }
        Integer yesterday = Integer.parseInt(RecordDate.Date()) - 1;
        boolean flag1 = redisUtil.hasKey(EnglishRedis.Phonetic + userId + ":" + yesterday + EnglishRedis.Done);
        if (flag1) {
            redisToDbService.PhoneticToDoToDB(userId, yesterday.toString());
        }

        //查出需要检测的所有音标
        List<String> idList = phoneticRecordDao.findPhoneticCheck(userId);
        if (idList == null) {
            return null;
        }

        //查询单词选音标
        List<String> idListWordPhonetic = idList.stream().map(item -> EnglishRedis.wordPhonetic + item).collect(Collectors.toList());
        List<String> valueListWordPhonetic = redisTemplate.opsForValue().multiGet(idListWordPhonetic);
        List<WordPhoneticEntity> wordPhoneticEntity = JSONObject.parseArray(valueListWordPhonetic.toString(),WordPhoneticEntity.class);

        //查询音标选单词
        List<String> idListPhoneticWord = idList.stream().map(item -> EnglishRedis.phnoeticWord + item).collect(Collectors.toList());
        List<String> valueListPhoneticWord = redisTemplate.opsForValue().multiGet(idListPhoneticWord);
        List<PhoneticWordEntity> phoneticwordEntity = JSONObject.parseArray(valueListPhoneticWord.toString(), PhoneticWordEntity.class);

        //把单词选音标和音标选单词合并到一个实体
        List<PhoneticCheckModel> list = new ArrayList<>();
        for (int i = 0; i > wordPhoneticEntity.size(); i++) {
            PhoneticCheckModel phoneticCheckModel = new PhoneticCheckModel();
            phoneticCheckModel.setPhonetic(wordPhoneticEntity.get(i).getPhonetic());

            phoneticCheckModel.setWordAudio(wordPhoneticEntity.get(i).getWordAudio());
            phoneticCheckModel.setPhoneticCorrectAudio(wordPhoneticEntity.get(i).getCorrectAudio());
            phoneticCheckModel.setPhoneticCorrectPic(wordPhoneticEntity.get(i).getCorrectPic());
            phoneticCheckModel.setPhoneticFalseAudio(wordPhoneticEntity.get(i).getFalseAudio());
            phoneticCheckModel.setPhoneticFalsePic(wordPhoneticEntity.get(i).getFalsePic());

            phoneticCheckModel.setPhoneticAudio(phoneticwordEntity.get(i).getPhoneticAudio());
            phoneticCheckModel.setWordCorrectAudio(wordPhoneticEntity.get(i).getCorrectAudio());
            phoneticCheckModel.setWordCorrectPic(wordPhoneticEntity.get(i).getCorrectPic());
            phoneticCheckModel.setWordFalseAudio(wordPhoneticEntity.get(i).getFalseAudio());
            phoneticCheckModel.setWordFalsePic(wordPhoneticEntity.get(i).getFalsePic());
        }

        redisUtil.llSetAll(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckToDo, list);
        redisUtil.leftPop(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckToDo);
        return list.get(0);
    }

    @Override
    public PhoneticCheckModel nextPhonetic(String phonetic, Integer correct, Integer falseType) {
        String userId = UserUtil.getCurrentUser().getUserId();
        //把检测过的音标插入checkDone
        Map<String, String> record = Maps.newHashMap();
        record.put("userId", userId);
        record.put("phonetic", phonetic);
        record.put("isCheck", "1");
        redisUtil.lSet(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckDone, JSON.toJSONString(record));

        //把检测错误的音标插入到storechecktodo
        if (correct != 1) {
            Map<String, String> map = Maps.newHashMap();
            map.put("phonetic", phonetic);
            map.put("falseType", falseType.toString());
            redisUtil.sSet(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.StoreCheckToDo, JSON.toJSONString(map));
        }

        //判断redis是否还有,有就取出一条
        boolean flag = redisUtil.hasKey(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckToDo);
        if (!flag) {
            String str = (String) redisUtil.leftPop(EnglishRedis.Phonetic + userId + RecordDate.Date() + EnglishRedis.CheckToDo);
            PhoneticCheckModel phoneticCheckModel = JSON.parseObject(str, PhoneticCheckModel.class);
            return phoneticCheckModel;
        }
        return null;
    }
}
