package com.tfjybj.english.provider.service.common;

import com.alibaba.fastjson.JSON;
import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.model.*;
import com.tfjybj.english.provider.dao.WordDao;
import com.tfjybj.english.provider.dao.WordRecordDao;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.FastJsonWrapper;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("wordDetectionService")
public class WordDetectionService {

    @Autowired
    WordRecordDao wordRecordDao;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisToDbService redisToDbService;

    @Autowired
    RankService rankService;

    @Autowired
    WordDao wordDao;

    /**
     * 将正确的单词图片插入到Redis
     * @param userId 用户id
     * @return 执行是否成功
     * @author 陈广晗
     */
    public boolean CorrectPictureToRedis(String userId) {
        List<WordDetection> word = wordRecordDao.selectWord(userId);
        int result = redisUtil.llSetAll(EnglishRedis.Detection + userId + RecordDate.Date() + EnglishRedis.CheckToDo, word);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * 清除redis中已经完成的数据
     * @author 陈广晗
     * @param userId 用户id
     * @return 清除是否成功
     */
    public boolean RedisOperating(String userId) {
        if (!redisToDbService.doneToDB(userId)) {
            return false;
        }
        if (!redisToDbService.checkDoneToDB(userId)) {
            return false;
        }
        if (!redisToDbService.WrongToDB(userId)) {
            return false;
        }
        redisUtil.del(EnglishRedis.Detection+ userId + RecordDate.Date() + EnglishRedis.CheckToDo);
        if (!this.CorrectPictureToRedis(userId)) {
            return false;
        }
        return true;
    }

    /**
     * 单词检测界面初始化
     * @author 陈广晗
     * @param userId 用户id
     * @return 一条待检测的单词信息
     */
    public WordModel JoinWordDetection(String userId) {
        if (!this.RedisOperating(userId)) {

            return new WordModel();
        }
        WordModel wordModel=new WordModel();
        wordModel= this.ObtainPicture(userId);
        int cuontWord = wordRecordDao.queryToCheckWordsByUserId(userId,500);
        wordModel.setCountWord(cuontWord);
        return wordModel;
    }

    /**
     * 获取需要检测单词的图片音频和id
     * @author 陈广晗
     * @param userId 用户id
     * @return 待检测的单词信息
     */
    public WordModel ObtainPicture(String userId) {
        String correctPicture = (String) redisUtil.leftPop(EnglishRedis.Detection + userId + RecordDate.Date() + EnglishRedis.CheckToDo);
        if (correctPicture == null) {
            return new WordModel();
        }
        WordDetection wordDetection = JSON.parseObject(correctPicture, WordDetection.class);
        WordTemplate wordTEmplate = wordRecordDao.selectWordTemplate(wordDetection.getId());
        WordModel wordModel = new WordModel();
        wordModel.setId(wordDetection.getId());
        wordModel.setAudio(wordDetection.getAudio());
        wordModel.setWordPicture1(wordTEmplate.getRightPicture());
        wordModel.setWordPicture2(wordTEmplate.getOtherPicture1());
        wordModel.setWordPicture3(wordTEmplate.getOtherPicture2());
        wordModel.setWordPicture4(wordTEmplate.getOtherPicture3());
        return wordModel;
    }

    /**
     * 将检测数据放入到redis中,并返回下一个待检测信息
     * @author 陈广晗
     * @param checkWord 检测单词的信息
     * @return返回下一个待检测信息
     */
    public WordModel DetectNext(CheckWord checkWord, String userId) {
        if (checkWord.getStatus() == 1) {
            redisUtil.sSet(EnglishRedis.Record + userId + RecordDate.Date() + EnglishRedis.CheckWrong,
                    FastJsonWrapper.toJson(checkWord.getId()));
       }
        else {
           boolean flag = rankService.addE(userId,1);
        }
        redisUtil.sSet(EnglishRedis.Record + userId + RecordDate.Date() + EnglishRedis.CheckWord,
                    FastJsonWrapper.toJson(checkWord));
        WordModel wordModel = new WordModel();
        wordModel = this.ObtainPicture(userId);
        if (wordModel.getId()==null){
            redisToDbService.checkDoneToDB(userId);
            redisToDbService.WrongToDB(userId);
        }
        return wordModel;
    }

    /**
     * 将单词对应的错误图片插入到Template表
     * @auter 陈广晗
     */
    public void toWordTemplate (){
        List<WordEntity> wordEntityList = wordDao.findAllWord();
        List<WordEntity> daorulist=new ArrayList<WordEntity>();

        for (int i=0;i<wordEntityList.size();i++){
            int a=2;
            int b=4;
            int c=8;
            WordEntity wordEntity = new WordEntity();
            a = i+a;
            if(a>wordEntityList.size()-1){
                a=1;
            }
            b = i+b;
            if(b>wordEntityList.size()-1){
                b=1;
            }
            c = i+c;
            if(c>wordEntityList.size()-1){
                c=1;
            }
            wordEntity.setId(wordEntityList.get(i).getId());
            wordEntity.setWordPicture1(wordEntityList.get(i).getWordPicture1());
            wordEntity.setWordPicture2(wordEntityList.get(a).getWordPicture2());
            wordEntity.setWordPicture3(wordEntityList.get(b).getWordPicture3());
            wordEntity.setWordPicture4(wordEntityList.get(c).getWordPicture4());

            daorulist.add(wordEntity);
//            wordEntity.setId(null);
        }
        wordDao.insertBatch(daorulist);
    }
}
