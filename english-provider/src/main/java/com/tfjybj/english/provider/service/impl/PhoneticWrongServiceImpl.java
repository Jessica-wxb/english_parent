package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Maps;
import com.tfjybj.english.entity.PhoneticWrongEntity;
import com.tfjybj.english.model.PhoneticStoreModel;
import com.tfjybj.english.model.PhoneticWordModel;
import com.tfjybj.english.model.PhoneticWrongModel;
import com.tfjybj.english.model.WordPhoneticModel;
import com.tfjybj.english.provider.dao.PhoneticWrongDao;
import com.tfjybj.english.provider.service.PhoneticWrongService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.FastJsonWrapper;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;

/**
 * PhoneticWrongService接口实现类
 * ${base}表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Service("phoneticWrongService")
public class PhoneticWrongServiceImpl extends BaseServicePlusImpl<PhoneticWrongDao,PhoneticWrongEntity> implements PhoneticWrongService {

	//region 模板生成
    @Resource
    private PhoneticWrongDao phoneticWrongDao;

	//endregion
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedisToDbService redisToDbService;
    /* **********************************以下为非模板生成的内容********************************* */
    @Override
    public PhoneticStoreModel Initialisation(String userId) {
        String correctPicture = (String) redisUtil.leftPop(EnglishRedis.Phonetic + userId+":" + RecordDate.Date() + EnglishRedis.StoreCheckToDo);
        if (StringUtil.isEmpty(correctPicture)){
            redisToDbService.phoneticStoreToDB(userId);
            return null;
        }
        PhoneticStoreModel phoneticStoreModel= JSON.parseObject(correctPicture, PhoneticStoreModel.class);
        if (phoneticStoreModel.getFalseType()==0){
            String phonetic = redisUtil.get(EnglishRedis.wordPhonetic+phoneticStoreModel.getPhonetic());
            WordPhoneticModel wordPhoneticModel= JSON.parseObject(phonetic, WordPhoneticModel.class);
            phoneticStoreModel.setAudio(wordPhoneticModel.getWordAudio());
            phoneticStoreModel.setCorrectAudio(wordPhoneticModel.getCorrectAudio());
            phoneticStoreModel.setCorrectPic(wordPhoneticModel.getCorrectPic());
            phoneticStoreModel.setFalseAudio(wordPhoneticModel.getFalseAudio());
            phoneticStoreModel.setFalsePic(wordPhoneticModel.getFalsePic());
            return phoneticStoreModel;
        }else {
            String phonetic = redisUtil.get(EnglishRedis.phnoeticWord+phoneticStoreModel.getPhonetic());
            PhoneticWordModel phoneticWordModel= JSON.parseObject(phonetic, PhoneticWordModel.class);
            phoneticStoreModel.setAudio(phoneticWordModel.getPhoneticAudio());
            phoneticStoreModel.setCorrectAudio(phoneticWordModel.getCorrectAudio());
            phoneticStoreModel.setCorrectPic(phoneticWordModel.getCorrectPic());
            phoneticStoreModel.setFalseAudio(phoneticWordModel.getFalseAudio());
            phoneticStoreModel.setFalsePic(phoneticWordModel.getFalsePic());
            return phoneticStoreModel;
        }
    }

    @Override
    public PhoneticStoreModel Modity(PhoneticWrongModel phoneticWrongModel) {
        redisUtil.sSet(EnglishRedis.Phonetic + phoneticWrongModel.getUserId()+":" + RecordDate.Date() + EnglishRedis.StoreDone,JSON.toJSONString(phoneticWrongModel));
        if (phoneticWrongModel.getIsStore()==0){
            Map<String, String> map = Maps.newHashMap();
            map.put("phonetic", phoneticWrongModel.getPhonetic());
            map.put("falseType", String.valueOf(phoneticWrongModel.getFalseType()));
            redisUtil.rghitSet(EnglishRedis.Phonetic + phoneticWrongModel.getUserId()+":" + RecordDate.Date() + EnglishRedis.StoreCheckToDo, FastJsonWrapper.toJson(map));
        }
        return this.Initialisation(phoneticWrongModel.getUserId());
    }
}
