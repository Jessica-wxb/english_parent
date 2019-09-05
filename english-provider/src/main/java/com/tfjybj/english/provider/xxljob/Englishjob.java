//package com.tfjybj.english.provider.xxljob;
//
//import com.tfjybj.english.provider.dao.UserInfoDao;
//import com.tfjybj.english.provider.service.common.RedisToDbService;
//import com.tfjybj.english.utils.RecordDate;
//import com.tfjybj.english.utils.cache.RedisUtil;
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.handler.IJobHandler;
//import com.xxl.job.core.handler.annotation.JobHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@JobHandler(value="Englishjob")
//@Component
//public class Englishjob extends IJobHandler {
//
//    private String ENGLISH_RANK = "English:Rank";
//    private String ENGLISH_USERINFO = "English:UserInfo";
//    private String ENGLISH_RECORD = "English:Record:";
//
//    @Autowired
//    RedisUtil redisUtil;
//
//    @Autowired
//    UserInfoDao userInfoDao;
//
//    @Autowired
//    RedisToDbService redisToDbService;
//
//    @Override
//    public ReturnT<String> execute(String s) throws Exception {
//        Set<String> keys = redisUtil.keys(ENGLISH_RECORD + "*" + RecordDate.Date() + "*");
//        String s1 = null;
//        List<String> list = new ArrayList<>();
//        for (String s2:keys) {
//            s1= s2.substring(15,37);
//            list.add(s1);
//        }
//        list.forEach(item ->guangHanMethod(item));
//        return  null;
//    }
//    void guangHanMethod(String userId){
//        redisToDbService.doneToDB(userId);
//        redisToDbService.checkDoneToDB(userId);
//        redisToDbService.StoreDoneToDB(userId);
//        redisToDbService.WrongToDB(userId);
//        redisToDbService.doneToDB(userId);
//    }
//}
