package com.tfjybj.english.provider.service.common;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tfjybj.english.entity.WordRecordEntity;
import com.tfjybj.english.entity.WordWrongEntity;
import com.tfjybj.english.model.CheckWord;
import com.tfjybj.english.model.StoreCheckWord;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.WordRecordDao;
import com.tfjybj.english.provider.dao.WordWrongDao;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    /**
     * 将redis中学习单词的数据同步到单词记录表中去
     * @author 陈广晗
     * @param userId 用户id
     * @return 执行是否成功
     */
    public boolean doneToDB(String userId){
        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Record + userId+ RecordDate.Date()+EnglishRedis.Done);
        if (CollectionUtils.isEmpty(doneStr)){
            return true;
        }
        List<String> list =  JSONObject.parseArray(doneStr.toString(), String.class);
        List<WordRecordEntity> wordRecordEntityList = list.stream()
                .map(e -> new WordRecordEntity(IdWorker.getIdStr(),userId,e))
                .collect(Collectors.toList());
        long result = wordRecordDao.insertBatch(wordRecordEntityList);

        if(result > 0){
            redisUtil.del(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.Done);
            return true;
        }
        return false;
    }

    /**
     * 将redis中单词检测过数据同步到单词记录表中去
     * @author 陈广晗
     * @param userId 用户id
     * @return 执行是否成功
     */
    public boolean checkDoneToDB(String userId){
        Set<Object> doneStr = redisUtil.sGet(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.CheckWord);
        if (CollectionUtils.isEmpty(doneStr)){
            return true;
        }
        List<CheckWord> list =  JSONObject.parseArray(doneStr.toString(), CheckWord.class);
        List<WordRecordEntity> wordRecordEntityList = list.stream()
                .map(e -> new WordRecordEntity(e.getId(),userId,e.getStatus()))
                .collect(Collectors.toList());
        long result = wordRecordDao.updateStatus(wordRecordEntityList);
        if(result > 0){
            redisUtil.del(EnglishRedis.Record + userId+RecordDate.Date()+EnglishRedis.CheckWord);
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
}
