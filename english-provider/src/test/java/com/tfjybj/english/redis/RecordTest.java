package com.tfjybj.english.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tfjybj.english.TestBaseProject;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.model.RankModel;
import com.tfjybj.english.model.UserNewpictureModel;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.dao.UserSetDao;
import com.tfjybj.english.provider.service.NewPictureService;
import com.tfjybj.english.provider.service.WordService;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.provider.service.common.WordListService;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class RecordTest extends TestBaseProject {

    private String ENGLISH_RANK = "English:Rank";
    private String ENGLISH_USERINFO = "English:UserInfo";
    private String ENGLISH_RECORD = "English:Record:";

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private WordListService wordService;

    @Autowired
    private NewPictureService newPictureService;

    @Autowired
    UserSetDao userSetDao;

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    RedisToDbService redisToDbService;

    @Test
    public void testByUserId() {
        UserSetEntity userSetEntity = new UserSetEntity();
        userSetEntity.setUserId("123");
        userSetEntity.setIsTurnAuto(1);
        userSetEntity.setPlayNums(3);
        userSetEntity.setCreateTime(new Date());
        String usersetjson = JSON.toJSONString(userSetEntity);
        UserSetEntity userSetEntity1 = JSON.parseObject(usersetjson, UserSetEntity.class);
        System.out.println(userSetEntity1.toString());
    }

    @Test
    public void testWordInsertRedis() {

        List<WordModel> wordModels = wordService.wordInsertRedis();
        System.out.println(wordModels.toString());
    }

    @Test
    public void testfindWordByName() {

        List<WordModel> wordModels = wordService.findWordByNameLR("ic");
        List<WordModel> wordModels2 = wordService.findWordByNameR("ic");
        System.out.println(wordModels.toString());
        System.out.println(wordModels2.toString());

    }

    @Test
    public void testnewPictureInsertRedis() {
        List<UserNewpictureModel> list = newPictureService.newPictureInsertRedis();
        System.out.println(list.toString());
    }

    @Test
    public void testqueryPageAll() {
        List<WordModel> list = wordService.queryPageAll(1, 10);
        System.out.println(list.toString());
        List<WordModel> list2 = wordService.queryPageAll(2, 10);
        System.out.println(list2.toString());
    }

    @Test
    public void jobdongke() {
        Map<Object, Object> rankMap = redisUtil.hmget(ENGLISH_RANK);
        List<RankModel> rankList = rankMap.entrySet().stream().map(e -> JSON.parseObject(String.valueOf(e.getValue()), RankModel.class)).collect(Collectors.toList());
        userInfoDao.modflyEB(rankList);
        System.out.println(rankMap.toString());
    }

    @Test
    public void jobguanghan() {
        Set<String> keys = redisUtil.keys(ENGLISH_RECORD + "*" + RecordDate.Date()  + "*");
        System.out.println(RecordDate.Date());
        String s1 = null;
        List<String> list = new ArrayList<>();
        for (String s:keys) {
            s1= s.substring(15,37);
            list.add(s1);
        }
        list.forEach(item ->tests(item));
        System.out.println(s1);

    }

     void tests(String userId){
        redisToDbService.doneToDB(userId);
         redisToDbService.checkDoneToDB(userId);
         redisToDbService.StoreDoneToDB(userId);
         redisToDbService.WrongToDB(userId);
         redisToDbService.doneToDB(userId);
    }
}








