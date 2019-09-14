package com.tfjybj.english.redis;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.google.common.collect.Lists;
import com.tfjybj.english.TestBaseProject;
import com.tfjybj.english.model.HomePageNumsModel;
import com.tfjybj.english.model.RankLocalModel;
import com.tfjybj.english.model.RankModel;
import com.tfjybj.english.provider.service.common.HomePageService;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class homepageUnTest {

    @Test
    public void testStayWords(){
        
       /* for(int i = 0;i<1000;i++){
            System.out.println(IdWorker.getIdStr());
        }*/
        RankModel rankModel = new RankModel();
        rankModel.setUserId("123");
        rankModel.setUserName("234");

        String str = rankModel.toString();
        String str1 = JSON.toJSONString(rankModel);
        System.out.println(str);
        System.out.println(str1);



    }

}
