package com.tfjybj.english.provider.service.common;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tfjybj.english.TestBaseProject;
import com.tfjybj.english.model.RankModel;
import com.tfjybj.english.model.NewPictureAddress;
import com.tfjybj.english.model.WordModel;

import com.tfjybj.english.model.WordTemplteModel;
import com.tfjybj.english.provider.service.WordService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class WordWrongCommonServiceTest extends TestBaseProject {


    private String ENGLIST_NEWPICTURE = "ENGLISH:NEWPICTURE:";
    @Autowired
    WordWrongCommonService wordWrongCommonService;

    @Autowired
    RankService rankService;

//    private RedisUtil<Object> redisUtil;
    @Autowired
    WordService wordDao;
    @Test
    public void queryStoreWord() {
//        Systemtring userId = "1";
//        WordModel wordModel = wordWrongCommonService.queryStoreWord(userId);
//        System.out.println(wordModel.toString());
    }

    @Test
    public void queryNextStoreWord() {
        WordModel wordWrongModel = wordWrongCommonService.queryNextStoreWord("1");
        System.out.println(wordWrongModel.toString());
    }

    @Test
    public void queryStoreCheckWord() {
//        WordModel wordWrongModel = wordWrongCommonService.queryStoreCheckWord("1");
//        System.out.println(wordWrongModel.toString());
    }

    @Test
    public void queryNextStoreCheckWord() {
//        WordTemplteModel wordWrongModel = wordWrongCommonService.queryNextStoreCheckWord("1");
//        System.out.println(wordWrongModel.toString());
}

//    @Test
//    public void updateStoreWord() {
//        WordModel falg = wordWrongCommonService.updateStoreWord("1","1140955044372324359");
//        WordModel falg1 = wordWrongCommonService.updateStoreCheckWord("1","1140955044372324359",false);
//        System.out.println(falg + " --- " + falg1);
//    }
}
