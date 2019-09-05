package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordWrongEntity;
import com.tfjybj.english.model.CheckWord;
import com.tfjybj.english.model.WordWrongModel;
import com.tfjybj.english.provider.dao.WordWrongDao;
import com.tfjybj.english.provider.service.WordWrongService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * WordWrongService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("wordWrongService")
public abstract class WordWrongServiceImpl extends BaseServicePlusImpl<WordWrongDao,WordWrongEntity> implements WordWrongService {
	
	//region 模板生成
    @Resource
    private WordWrongDao wordWrongDao;



    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedisToDbService redisToDbService;



    /**
     * 查询用户待学习数据
     * @param userId
     * @return
     */
//    @Override
//    public List<WordWrongModel> queryWrongByUserId(String userId) {
//        CheckWord checkWord = new CheckWord();
//        //判断redis中是否存在 CheckWrong
//        if(!redisUtil.hasKey("English:Record:"+checkWord.getUserId()+ RecordDate.Date()+":CheckWrong")){
//            //同步数据库中tn_word_srong tn_word_record 中 is_study  is_check = 0 的数据
//            redisToDbService.WrongToDB(userId);
//        }else if (!redisUtil.hasKey("English:Store"+ userId + RecordDate.Date()+":StoreDone")){            //判断Redis中是否存在storeDone
//            //同步redis中is_study = 1 is_check = 0 的数据
//            redisToDbService.StoreDoneToDB(userId);
//        }
//        //查询数据
//        List<WordWrongModel> wrongModelList = wordWrongDao.queryWrongByUserId(userId);
//        //判断数据长度 >0 存入Redis <0 返回null
//        if(wrongModelList.size() >  0){
//            Map<String,Object> map = wrongModelList.stream().collect(Collectors.toMap(WordWrongModel::getUserId,WordWrongModel::toString));
//            redisUtil.hmset("English:Store"+ userId + RecordDate.Date()+":StoreToDo",map);
////            redisUtil.set("English_Store_To_Do",String.valueOf(wrongModelList));
//            return wrongModelList;
//        }else{
//            return null;
//        }
//    }

    /**
     * 查询用户未检测数据
     * @param userId
     * @return
     */
//    @Override
//    public List<WordWrongModel> queryCheckByUserId(String userId) {
//        //判断ENGLISH_STORE_DONE 是否存在
//        if(!redisUtil.hasKey("English:Store"+ userId + RecordDate.Date()+":StoreDone")){
//            //存在: 同步数据到tn_word_wrong
//        redisToDbService.StoreDoneToDB(userId);
//        }else if(!redisUtil.hasKey("English:Store"+ userId + RecordDate.Date()+":StoreCheckDone")){//不存在: 判断ENGLISH_STORE_CHECK_DONE 是否存在
//            //存在 : 同步数据到tn_word_wrong
//        redisToDbService.StoreCheckDoneToDB(userId);
//        }
//        //不存在: 查询用户待归仓检测数据
//        List<WordModel> wordWrongModelList =  wordWrongDao.queryCheckByUserId(userId);
//        //判断获得的数据长度
//        if (wordWrongModelList.size() > 0 ){
//            //>0 数据写入Redis
//            Map<String,Object> map = wordWrongModelList.stream().collect(Collectors.toMap(WordModel ::getId,WordWrongModel::toString));
//            redisUtil.hmset("English:Store"+ userId + RecordDate.Date()+":StoreCheckDone",map);
////            redisUtil.set("English_STORE_To_Do",String.valueOf(wordWrongModelList));
//            return wordWrongModelList;
//        }else {
//            //< 0 返回 null
//            return null ;
//        }
////        return wordWrongDao.queryCheckByUserId(userId);
//    }

    /**
     * 根据用户Id 单词Id  更新用户学习状态
     * @param userId 用户Id
     * @param wordId
     * @return
     */
    @Override
    public boolean updateStudyByUserId(String userId, String wordId) {
        wordWrongDao.updateStudyByUserId(userId, wordId);
        // TODO 从 Redis ENGLISH_STORE_TO_DO 中删除已经更新的数据,并且插入到ENGLISH_STORE_DON
        // TODO 判断ENGLISH_STORE_TO_DO .size > 0 ?  是 切换显示下一条数据
        //TODO <0 将数据更新到数据库,清空ENGLISH_STORE_TO_DO
        return true ;
    }

    @Override
    public boolean updateCheckByUserId(String userId, String wordId) {
        wordWrongDao.updateCheckByUserId(userId,wordId);
        // TODO 从 Redis ENGLISH_STORE_TO_DO 中删除已经更新的数据,并且插入到ENGLISH_STORE_DON
        // TODO 判断ENGLISH_STORE_TO_DO .size > 0 ?  是 切换显示下一条数据
        //TODO <0 将数据更新到数据库,清空ENGLISH_STORE_TO_DO
        return false;
    }
}
