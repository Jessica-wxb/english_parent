package com.tfjybj.english.provider.service.common;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tfjybj.english.entity.*;
import com.tfjybj.english.model.CheckWord;
import com.tfjybj.english.model.PhoneticWrongModel;
import com.tfjybj.english.model.StoreCheckWord;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.*;
import com.tfjybj.english.entity.PhoneticRecordEntity;
import com.tfjybj.english.entity.WordRecordEntity;
import com.tfjybj.english.entity.WordWrongEntity;
import com.tfjybj.english.provider.dao.PhoneticRecordDao;
import com.tfjybj.english.provider.dao.WordRecordDao;
import com.tfjybj.english.provider.dao.WordWrongDao;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("redisToDbService")
public class RedisToDbService {


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    WordRecordDao wordRecordDao;

    @Autowired
    WordWrongDao wordWrongDao;

    @Autowired
    PhoneticWrongDao phoneticWrongDao;

    @Autowired
    PhoneticRecordDao phoneticRecordDao;


    /**
     * 将redis中学习单词的数据同步到单词记录表中去
     *
     * @param userId 用户id
     * @return 执行是否成功
     * @author 陈广晗
     */
    public boolean doneToDB(String userId) {
        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Record + userId + RecordDate.Date() + EnglishRedis.Done);
        if (CollectionUtils.isEmpty(doneStr)) {
            return true;
        }
        List<String> list = JSONObject.parseArray(doneStr.toString(), String.class);
        List<WordRecordEntity> wordRecordEntityList = list.stream()
                .map(e -> new WordRecordEntity(IdWorker.getIdStr(), userId, e))
                .collect(Collectors.toList());
        long result = wordRecordDao.insertBatch(wordRecordEntityList);

        if (result > 0) {
            redisUtil.del(EnglishRedis.Record + userId + RecordDate.Date() + EnglishRedis.Done);
            return true;
        }
        return false;
    }

    /**
     * 将redis中单词检测过数据同步到单词记录表中去
     *
     * @param userId 用户id
     * @return 执行是否成功
     * @author 陈广晗
     */
    public boolean checkDoneToDB(String userId) {
        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Record + userId + RecordDate.Date() + EnglishRedis.CheckWord);
        if (CollectionUtils.isEmpty(doneStr)) {
            return true;
        }
        List<CheckWord> list = JSONObject.parseArray(doneStr.toString(), CheckWord.class);
        List<WordRecordEntity> wordRecordEntityList = list.stream()
                .map(e -> new WordRecordEntity(e.getId(), userId, e.getStatus()))
                .collect(Collectors.toList());
        long result = wordRecordDao.updateStatus(wordRecordEntityList);
        if (result > 0) {
            redisUtil.del(EnglishRedis.Record + userId + RecordDate.Date() + EnglishRedis.CheckWord);
            return true;
        }
        return false;
    }

    /**
     * 将redis中单词检测错误的单词同步到错题表中去
     * @author 陈广晗
     * @param userId 用户id
     * @return 执行是否成功
     */
    public boolean WrongToDB(String userId){
        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.CheckWrong);
        if (CollectionUtils.isEmpty(doneStr)){
            return true;
        }
        List<String> list =  JSONObject.parseArray(doneStr.toString(), String.class);
        List<WordWrongEntity> wordWrongEntityList = list.stream()
                .map(e -> new WordWrongEntity(IdWorker.getIdStr(),userId,e))
                .collect(Collectors.toList());
        long result = wordWrongDao.insertBatch(wordWrongEntityList);
        if(result > 0){
            redisUtil.del(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.CheckWrong);
            return true;
        }
        return false;
    }
    /**
     * 将已经在单词归仓中学习过的单词更新到数据库
     * @author 陈广晗
     * @param userId 用户id
     * @return 执行是否成功
     */
    public boolean StoreDoneToDB(String userId){
        redisUtil.del(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.StoreToDo);
        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.StoreDone);
        if (CollectionUtils.isEmpty(doneStr)){
            return true;
        }
        List<WordModel> list =  JSONObject.parseArray(doneStr.toString(), WordModel.class);
        List<WordWrongEntity> wordWrongEntityList = list.stream()
                .map(e -> new WordWrongEntity(userId,e.getId()))
                .collect(Collectors.toList());
        long result = wordWrongDao.updateStudy(wordWrongEntityList);
        redisUtil.del(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.StoreDone);
        return true;
    }

    /**
     * 将已经在归仓检测中检测过的单词状态更新到数据库
     * @author 陈广晗
     * @param userId 用户id
     * @return 执行是否成功
     */
    public boolean StoreCheckDoneToDB(String userId){
        redisUtil.del(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.StoreCheckToDo);
        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.StoreCheckDone);
        if (CollectionUtils.isEmpty(doneStr)){
            return true;
        }
        List<StoreCheckWord> list =  JSONObject.parseArray(doneStr.toString(), StoreCheckWord.class);
        List<WordWrongEntity> wordWrongEntityList = list.stream()
                .map(e -> new WordWrongEntity(userId,e.getId(),e.getCheck()))
                .collect(Collectors.toList());
        long result = wordWrongDao.updateCheck(wordWrongEntityList);
        redisUtil.del(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.StoreCheckDone);
        return true;
    }
    /**
     * 将Redis中已经完成音标归仓的数据同步到数据库
     * @param userId
     * @return
     */
    public boolean phoneticStoreToDB(String userId) {
        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.StoreDone);
        if (CollectionUtils.isEmpty(doneStr)) {
            return true;
        }
        List<PhoneticWrongModel> list = JSONObject.parseArray(doneStr.toString(), PhoneticWrongModel.class);
        List<PhoneticWrongEntity> phoneticWrongEntities = list.stream()
                .map(e -> new PhoneticWrongEntity(IdWorker.getIdStr(), e.getUserId(), e.getPhonetic(), e.getIsStore(), e.getFalseType()))
                .collect(Collectors.toList());
        long result = phoneticWrongDao.insertBatch(phoneticWrongEntities);
        redisUtil.del(EnglishRedis.Record + userId + ":" + RecordDate.Date() + EnglishRedis.StoreDone);
        return true;
    }



    /**
     * 将已经学习完的音标同步到DB
     *
     * @param userId 用户id
     * @param date   日期 例如(0905)
     * @return 执行是否成功
     * @author 闫伟强
     */
    public boolean PhoneticToDoToDB(String userId, String date) {
        boolean key = redisUtil.hasKey(EnglishRedis.Phonetic + userId + ":" + date + EnglishRedis.Done);
        if (key) {
            Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Phonetic + userId + ":" + date + EnglishRedis.Done);
            List<PhoneticRecordEntity> list = JSONObject.parseArray(doneStr.toString(), PhoneticRecordEntity.class);
            phoneticRecordDao.insertList(list);
            redisUtil.del(EnglishRedis.Phonetic + userId + ":" + date + EnglishRedis.Done);
        }
        return true;
    }

    /**
     * 将已经检测完的音标同步到DB
     *
     * @param userId 用户id
     * @return 执行是否成功
     * @author 闫伟强
     */
    public boolean PhoneticCheckDoneToDB(String userId) {
        boolean flag = redisUtil.hasKey(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckDone);
        if (flag) {
            Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckDone);
            List<PhoneticRecordEntity> list = JSONObject.parseArray(doneStr.toString(), PhoneticRecordEntity.class);
//            List<PhoneticRecordEntity> phoneticRecordEntities = list.stream()
//                    .map(e -> new PhoneticRecordEntity(IdWorker.getIdStr(), userId, e.getPhonetic(),e.getIsCheck()))
//                    .collect(Collectors.toList());
            phoneticRecordDao.modfiyIsCheck(list);
            redisUtil.del(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.CheckDone);
        }

        Integer yesterday = Integer.parseInt(RecordDate.Date()) - 1;
        boolean flag1 = redisUtil.hasKey(EnglishRedis.Phonetic + userId + ":" + yesterday + EnglishRedis.CheckDone);
        if (flag1) {
            Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Phonetic + userId + ":" + yesterday + EnglishRedis.CheckDone);
            List<PhoneticRecordEntity> list = JSONObject.parseArray(doneStr.toString(), PhoneticRecordEntity.class);
            List<PhoneticRecordEntity> phoneticRecordEntities = list.stream()
                    .map(e -> new PhoneticRecordEntity(IdWorker.getIdStr(), userId, e.getPhonetic(),e.getIsCheck()))
                    .collect(Collectors.toList());
        }
//        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Phonetic + userId + ":" + date + EnglishRedis.CheckDone);
//        List<PhoneficRecordEntity> list = JSONObject.parseArray(doneStr.toString(), PhoneficRecordEntity.class);
//        phoneticRecordDao.insertList(list);
        return true;
    }
}
