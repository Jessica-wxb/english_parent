package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.google.common.collect.Maps;
import com.tfjybj.english.entity.PhoneticRecordEntity;
import com.tfjybj.english.entity.PhoneticWordEntity;
import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordPhoneticEntity;
import com.tfjybj.english.model.PhoneticCheckModel;
import com.tfjybj.english.model.WordDetection;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.PhoneticRecordDao;
import com.tfjybj.english.provider.dao.PhoneticWordDao;
import com.tfjybj.english.provider.service.PhoneticCheckService;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("PhoneticCheckService")
public class PhoneticCheckServiceImpl extends BaseServicePlusImpl<PhoneticWordDao, PhoneticWordEntity> implements PhoneticCheckService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PhoneticWordDao phoneticWordDao;

    @Autowired
    RedisToDbService redisToDbService;

    @Autowired
    PhoneticRecordDao phoneticRecordDao;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @param
     *
     * @return 进入音标检测
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    @Override
    public PhoneticCheckModel findPhoneticCheck() {
        String userId =UserUtil.getCurrentUser().getUserId();
        redisToDbService.PhoneticCheckDoneToDB(userId);
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
        if (idList.size() == 0) {
            return null;
        }
        redisUtil.del(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckToDo);
        //查询单词选音标
        List<String> idListWordPhonetic = idList.stream().map(item -> EnglishRedis.wordPhonetic + item).collect(Collectors.toList());
        List<String> valueListWordPhonetic = redisTemplate.opsForValue().multiGet(idListWordPhonetic);
        List<WordPhoneticEntity> wordPhoneticEntity = JSONObject.parseArray(valueListWordPhonetic.toString(), WordPhoneticEntity.class);

        //查询音标选单词
        List<String> idListPhoneticWord = idList.stream().map(item -> EnglishRedis.phnoeticWord + item).collect(Collectors.toList());
        List<String> valueListPhoneticWord = redisTemplate.opsForValue().multiGet(idListPhoneticWord);
        List<PhoneticWordEntity> phoneticwordEntity = JSONObject.parseArray(valueListPhoneticWord.toString(), PhoneticWordEntity.class);

        //把单词选音标和音标选单词合并到一个实体
        List<PhoneticCheckModel> list = new ArrayList<>();
        for (int i = 0; i < wordPhoneticEntity.size(); i++) {
            PhoneticCheckModel phoneticCheckModel = new PhoneticCheckModel();
            phoneticCheckModel.setPhonetic(wordPhoneticEntity.get(i).getPhonetic());

            phoneticCheckModel.setWordAudio(wordPhoneticEntity.get(i).getWordAudio());
            phoneticCheckModel.setPhoneticCorrectAudio(wordPhoneticEntity.get(i).getCorrectAudio());
            phoneticCheckModel.setPhoneticCorrectPic(wordPhoneticEntity.get(i).getCorrectPic());
            phoneticCheckModel.setPhoneticFalseAudio(wordPhoneticEntity.get(i).getFalseAudio());
            phoneticCheckModel.setPhoneticFalsePic(wordPhoneticEntity.get(i).getFalsePic());

            phoneticCheckModel.setPhoneticAudio(phoneticwordEntity.get(i).getPhoneticAudio());
            phoneticCheckModel.setWordCorrectAudio(phoneticwordEntity.get(i).getCorrectAudio());
            phoneticCheckModel.setWordCorrectPic(phoneticwordEntity.get(i).getCorrectPic());
            phoneticCheckModel.setWordFalseAudio(phoneticwordEntity.get(i).getFalseAudio());
            phoneticCheckModel.setWordFalsePic(phoneticwordEntity.get(i).getFalsePic());
            list.add(phoneticCheckModel);
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
        redisUtil.sSet(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckDone, JSON.toJSONString(record));

        //把检测错误的音标插入到storechecktodo
        if (correct != 1) {
            Map<String, String> map = Maps.newHashMap();
            map.put("phonetic", phonetic);
            map.put("falseType", falseType.toString());
            redisUtil.lSet(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.StoreCheckToDo, JSON.toJSONString(map));
        }

        //判断redis是否还有,有就取出一条
        boolean flag = redisUtil.hasKey(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckToDo);
        if (flag) {
            String str = (String) redisUtil.leftPop(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckToDo);
            PhoneticCheckModel phoneticCheckModel = JSON.parseObject(str, PhoneticCheckModel.class);
            return phoneticCheckModel;
        }
        return null;
    }
}
