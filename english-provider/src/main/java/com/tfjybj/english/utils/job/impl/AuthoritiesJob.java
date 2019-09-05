package com.tfjybj.english.utils.job.impl;

import com.alibaba.fastjson.JSON;
import com.tfjybj.english.model.RankModel;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import com.tfjybj.english.utils.job.IBaseJob;
import com.tfjybj.english.utils.job.JobHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Quartz定时任务. <br>
 * 使用配置文件方式.
 */

@JobHander("authoritiesJob")
public class AuthoritiesJob implements IBaseJob {

    private String ENGLISH_RANK = "English:Rank";
    private String ENGLISH_USERINFO = "English:UserInfo";
    private String ENGLISH_RECORD = "English:Record:";

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    RedisToDbService redisToDbService;
//    @Autowired
//    private TestCommonService testCommonService;
//
////    @Autowired
////    private TestCommonService testCommonService;
//
//    @SuppressWarnings("unchecked")
//    @Scheduled(fixedRate = 1 * 10 * 1000)
//    public void task() {
//        UserModel userModel = testCommonService.userUpdate();
//        System.out.println(userModel.toString());
//    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void synchroCheckWord() {
        Set<String> keys = redisUtil.keys(ENGLISH_RECORD + "*" + RecordDate.Date() + "*");
        String s1 = null;
        List<String> list = new ArrayList<>();
        for (String s:keys) {
            s1= s.substring(15,37);
            list.add(s1);
        }
        list.forEach(item ->guangHanMethod(item));
    }

    void guangHanMethod(String userId){
        redisToDbService.doneToDB(userId);
        redisToDbService.checkDoneToDB(userId);
        redisToDbService.StoreDoneToDB(userId);
        redisToDbService.WrongToDB(userId);
        redisToDbService.doneToDB(userId);
    }

    @Scheduled(cron = "0/5 0 0 * * ?")
    @Override
    public void ReRank() {
        Map<Object, Object> rankMap = redisUtil.hmget(ENGLISH_RANK);
        List<RankModel> rankList = rankMap.entrySet().stream().map(e -> JSON.parseObject(String.valueOf(e.getValue()),RankModel.class)).collect(Collectors.toList());
        userInfoDao.modflyEB(rankList);
    }
}
