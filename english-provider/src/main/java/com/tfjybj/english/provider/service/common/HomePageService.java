package com.tfjybj.english.provider.service.common;

import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.model.HomePageNumsModel;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.PhoneticRecordDao;
import com.tfjybj.english.provider.dao.UserSetDao;
import com.tfjybj.english.provider.dao.WordRecordDao;
import com.tfjybj.english.provider.dao.WordWrongDao;
import com.tfjybj.english.provider.service.*;
import com.tfjybj.english.provider.service.impl.PhoneticWrongServiceImpl;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service("homePageService")

public class HomePageService  {

    private Integer MAX_WORDS = 500;

    @Resource
    WordRecordDao wordRecordDao;

    @Resource
    UserSetDao userSetDao;

 /*   @Autowired
    WordRecordService wordRecordService;*/

    @Autowired
    UserSetService userSetService;

    @Autowired
    RedisToDbService redisToDbService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PhoneticRecordDao phoneticRecordDao;

    @Autowired
    PhoneticWrongServiceImpl phoneticWrongService;
//    private RedisUtil<Object> redisUtil;
@Autowired
WordWrongDao wordWrongDao;
    /**
     * 【首页】右上角数据
     * @param userId
     * @return
     */
    public HomePageNumsModel stayWords(String userId){

        HomePageNumsModel homePageNumsModel = new HomePageNumsModel();
        homePageNumsModel.setUserId(userId);
        //学单词
        boolean isCogradient = redisToDbService.doneToDB(userId);
        if (isCogradient){
            //从数据库中查询出今天已学单词数量
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            String time2 = format2.format(Calendar.getInstance().getTime());
            int learnNum = wordRecordDao.queryLearnNumByuserId(userId,MAX_WORDS,time2);//今日已学单词数量
            int studyNum = userSetDao.queryStudyNumByUserId(userId);//今日设置的 学单词数量

            int stayWords = studyNum-learnNum;
            if (stayWords<=0 ){
                homePageNumsModel.setWordsStay(0);

            }else{
                homePageNumsModel.setWordsStay(stayWords);
            }
        }else{
            homePageNumsModel.setWordsStay(-1);
        }

        //单词检测
        boolean ischeckDoneToDB = redisToDbService.checkDoneToDB(userId);
        if(ischeckDoneToDB){
            homePageNumsModel.setToCheckNum( wordRecordDao.queryToCheckWordsByUserId(userId , MAX_WORDS));
        }else{
            homePageNumsModel.setToCheckNum(-1);
        }

        redisToDbService.WrongToDB(userId);

        homePageNumsModel.setCheckIsUsed(1);
        if(homePageNumsModel.getToCheckNum() == -1 ||homePageNumsModel.getToCheckNum() == 0){
            homePageNumsModel.setCheckIsUsed(0);
        }

        //归仓检测
        boolean StoreCheckToDB = redisToDbService.StoreCheckDoneToDB(userId);
        if(StoreCheckToDB){
            if(redisUtil.hasKey(EnglishRedis.Record + userId+ RecordDate.Date()+":StoreCheckToDo")){
               long  redisCheckToDoNums = redisUtil.lGetListSize(EnglishRedis.Record + userId+ RecordDate.Date()+":StoreCheckToDo");
                homePageNumsModel.setStoreCheckNums((int) redisCheckToDoNums);
                homePageNumsModel.setButtonIsWhat(0);//显示【归仓检测】
            }else {
                //查询该学生归仓待检测的记录
                Integer num =  wordWrongDao.queryNumCheck(userId);
                List<WordModel> wordModelList = wordWrongDao.queryCheckByUserId(userId,num);
                homePageNumsModel.setStoreCheckNums(wordModelList.size());
                if (wordModelList.size() ==  0  ){
                    homePageNumsModel.setButtonIsWhat(1);//显示单词归仓
                }else {
//                homePageNumsModel.setStoreCheckNums(wordRecordDao.queryStoreCheckNumsByUserId(userId,MAX_WORDS));
                    homePageNumsModel.setButtonIsWhat(0);//显示【归仓检测】
                }
            }
        }else{
            homePageNumsModel.setStoreCheckNums(-1);
        }
        //单词归仓
        boolean StoreDoneToDB = redisToDbService.StoreDoneToDB(userId);
        if(StoreDoneToDB){
            int i = wordRecordDao.queryStoreNumsByUserId(userId, MAX_WORDS);
            if (i!=0){
                homePageNumsModel.setStoreNums(wordRecordDao.queryStoreNumsByUserId(userId, MAX_WORDS));
                homePageNumsModel.setButtonIsWhat(1);//显示【单词归仓】
            }
            else {
                homePageNumsModel.setStoreNums(0);
            }
        }else{
            homePageNumsModel.setStoreNums(-1);
        }

        homePageNumsModel.setStoreIsUsed(1);
        if(homePageNumsModel.getStoreNums() ==0 ||homePageNumsModel.getStoreNums() == -1) {
            if (homePageNumsModel.getStoreCheckNums() == 0 || homePageNumsModel.getStoreCheckNums() == -1) {
                homePageNumsModel.setStoreIsUsed(0);
            }
        }

        // 将已经检测完的音标同步到DB
        redisToDbService.PhoneticCheckDoneToDB(userId);
        redisToDbService.PhoneticToDoToDB(userId, RecordDate.Date());
        homePageNumsModel.setPhoneticCheckNums(phoneticRecordDao.checkPhoneticNums(userId));
        homePageNumsModel.setPhoneticStoreToDo(phoneticWrongService.FindStoreNums());
        return homePageNumsModel;
    }

}
