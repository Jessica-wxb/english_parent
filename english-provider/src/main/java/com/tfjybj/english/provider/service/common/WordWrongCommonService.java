package com.tfjybj.english.provider.service.common;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.model.*;
import com.tfjybj.english.provider.dao.WordRecordDao;
import com.tfjybj.english.provider.dao.WordWrongDao;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.json.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("wordWrongCommonService")
public class WordWrongCommonService {

    private int MAX_WORDS = 500;


    @Autowired
    WordDetectionService wordDetectionService;
    @Autowired
    RedisToDbService redisToDbService;

    @Autowired
    WordWrongDao wordWrongDao;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    WordRecordDao wordRecordDao;

    /**
     * 查询归仓未学习的单词，插入Redis
     *
     */
    public WordModel queryStoreWord(String userId){
        redisToDbService.doneToDB(userId);
        redisToDbService.checkDoneToDB(userId);
        redisToDbService.StoreDoneToDB(userId);

        //查询该学生未学习的记录

        List<WordModel>  wordModelList = wordWrongDao.queryStudyByUserId(userId);
        if(CollectionUtils.isEmpty(wordModelList)){
            return null;
        }

        redisUtil.lSetAll(EnglishRedis.Record + userId+RecordDate.Date()+":StoreToDo",wordModelList);
        WordModel wordWrongModel = JSON.parseObject(String.valueOf(redisUtil.leftPop(EnglishRedis.Record + userId+RecordDate.Date()+":StoreToDo")), WordModel.class);
        if(wordWrongModel == null){
            return null;
        }
        redisUtil.sSet(EnglishRedis.Record + userId+RecordDate.Date()+":StoreDone", JSON.toJSONString(wordWrongModel));
//        redisUtil.leftPop(EnglishRedis.Record + userId + RecordDate.Date() +":StoreToDo");
        return wordWrongModel;
    }

    /**
     * 单词归仓待学习数据，切换下一个
     * @param userId
     * @return
     */
    public WordAndTypeModel queryNextStoreWord(String userId){
        WordAndTypeModel wordAndTypeModel = new WordAndTypeModel();

        if(redisUtil.hasKey(EnglishRedis.Record + userId + RecordDate.Date() +":StoreToDo")){
            //取出缓存第一条数据
            WordModel wordWrongModel = JSON.parseObject(String.valueOf( redisUtil.leftPop(EnglishRedis.Record + userId + RecordDate.Date() +":StoreToDo")),WordModel.class);
            //插入待检测Redis
            redisUtil.sSet(EnglishRedis.Record + userId+RecordDate.Date()+":StoreDone", JSON.toJSONString(wordWrongModel));
            redisUtil.sSet(EnglishRedis.Record + userId+RecordDate.Date()+":StoreCheckToDo", JSON.toJSONString(wordWrongModel));
            BeanUtils.copyProperties(wordWrongModel,wordAndTypeModel);
            if (!redisUtil.hasKey(EnglishRedis.Record + userId + RecordDate.Date() +":StoreToDo")){
                redisToDbService.StoreDoneToDB(userId);
                wordAndTypeModel.setType("1");
            }
                //返回显示下一条数据
                return  wordAndTypeModel ;
        }
        return null;
    }

    /**
     * 查询归仓未检测的单词数据，插入Redis
     * @param userId 用户Id
     * @return
    */
    public WordTemplteModel queryStoreCheckWord(String userId){
        redisToDbService.doneToDB(userId);
        redisToDbService.checkDoneToDB(userId);
        redisToDbService.StoreDoneToDB(userId);
        redisToDbService.StoreCheckDoneToDB(userId);
//        redisUtil.del(EnglishRedis.Record + userId+RecordDate.Date()+":StoreCheckToDo");

        // 查询用户单词检测激励数据
//        Integer recordNum = wordWrongDao.queryNumRecord(userId);
        //查询该学生归仓待检测的记录
        Integer num =  wordWrongDao.queryNumCheck(userId);
        List<WordModel>  wordModelList = wordWrongDao.queryCheckByUserId(userId,num);
        if(CollectionUtils.isEmpty(wordModelList)){
            return null;
        }
        redisUtil.lSetAll(EnglishRedis.Record + userId + RecordDate.Date()+":StoreCheckToDo",wordModelList);
        WordTemplteModel wordModel = JSON.parseObject(String.valueOf(redisUtil.leftPop(EnglishRedis.Record + userId+RecordDate.Date()+":StoreCheckToDo")), WordTemplteModel.class);
//        redisUtil.rghitSet(EnglishRedis.Record + userId+RecordDate.Date()+":StoreCheckToDo",JSON.toJSONString(wordModel));
//        wordModel.setCountWord(wordWrongDao.queryNumCheck(userId)) ;

        return wordModel;
    }

    /**
     * 单词归仓待检测数据，切换下一个
     * @param userId 用户ID
     * @return
     */
    public WordTemplteModel queryNextStoreCheckWord(String userId,String wordId , Integer isCheck){
        WordTemplteModel wordWrongModel = new WordTemplteModel();
        //需要在检测
        if (isCheck == 0){
            if(redisUtil.hasKey(EnglishRedis.Record + userId + RecordDate.Date() +":StoreCheckToDo")){
                wordWrongModel = JSON.parseObject(String.valueOf( redisUtil.leftPop(EnglishRedis.Record + userId + RecordDate.Date() +":StoreCheckToDo")),WordTemplteModel.class);
                redisUtil.rghitSet(EnglishRedis.Record + userId+RecordDate.Date()+":StoreCheckToDo",JSON.toJSONString(wordWrongModel));
//                redisUtil.lSet(EnglishRedis.Record + userId + RecordDate.Date() +":StoreCheckToDo",JSON.toJSONString(wordWrongModel));

//                WordCheckAndTypeModel wordTemplteModel = new WordCheckAndTypeModel();
//                BeanUtils.copyProperties(wordWrongModel,wordTemplteModel);
//                wordTemplteModel.setType("0");
                return wordWrongModel;
            }

        }
        if(isCheck == 1){
            redisUtil.rightPop(EnglishRedis.Record + userId + RecordDate.Date() +":StoreCheckToDo");
            StoreCheckWord storeCheckWord = new StoreCheckWord();
            storeCheckWord.setId(wordId);
            storeCheckWord.setCheck(isCheck);
            redisUtil.sSet(EnglishRedis.Record + userId+RecordDate.Date()+":StoreCheckDone",JSON.toJSONString(storeCheckWord));
            if(redisUtil.hasKey(EnglishRedis.Record + userId + RecordDate.Date() +":StoreCheckToDo")){
                wordWrongModel = JSON.parseObject(String.valueOf( redisUtil.leftPop(EnglishRedis.Record + userId + RecordDate.Date() +":StoreCheckToDo")),WordTemplteModel.class);
                redisUtil.rghitSet(EnglishRedis.Record + userId+RecordDate.Date()+":StoreCheckToDo",JSON.toJSONString(wordWrongModel));
//                WordCheckAndTypeModel wordTemplteModel = new WordCheckAndTypeModel();
//                BeanUtils.copyProperties(wordWrongModelNext,wordTemplteModel);
//                wordTemplteModel.setType("0");
                return wordWrongModel;
            }
        }
        return null;
    }

    /**
     * 更新单词归仓记录学习状态
     * @param userId 用户ID
     * @param wordId 单词ID
     * @return
     */
    public WordAndTypeModel updateStoreWord(String userId,String wordId){
       wordWrongDao.updateStudyByUserId(userId,wordId);
       return queryNextStoreWord(userId);
    }

    /**
     * 更新归仓检测记录判断状态
     * @param userId 用户ID
     * @param wordId 单词ID
     * @param isCheck 是否正确
     * @return
     */
    public WordTemplteModel updateStoreCheckWord(String userId,String wordId,Integer isCheck){
        if(isCheck  == 1 ){
          wordWrongDao.updateCheckByUserId(userId,wordId);
        }
        return  queryNextStoreCheckWord(userId,wordId,isCheck);
    }


    /**
     * 获得用户未检测数
     * @param userId
     * @return
     */
    public Integer queryNumCheck(String userId) {
        return wordWrongDao.queryNumCheck(userId);
    }


    /**
     * 查询用户获得用户未检测数
     * @param userId
     * @return
     */
    public Integer queryNumStudy(String userId) {
        return wordWrongDao.queryNumStudy(userId);
    }

    public Integer queryStoreNumsByUserId(String userId) {
        //单词归仓
            return wordRecordDao.queryStoreNumsByUserId(UserUtil.getCurrentUser().getUserId(),MAX_WORDS);
    }

    public Integer queryStoreCheckToDoWord(String userId) {
        Integer Cnum = wordWrongDao.queryNumCheck(userId);
        List<WordModel> wordList = wordWrongDao.queryCheckByUserId(userId,Cnum);
        return  wordList.size();
    }
}
