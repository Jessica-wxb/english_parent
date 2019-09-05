package com.tfjybj.english.redis;

import com.tfjybj.english.TestBaseProject;
import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordRecordEntity;
import com.tfjybj.english.entity.WordWrongEntity;
import com.tfjybj.english.model.HomePageNumsModel;
import com.tfjybj.english.model.MineModel;
import com.tfjybj.english.model.RankLocalModel;
import com.tfjybj.english.provider.dao.WordDao;
import com.tfjybj.english.provider.dao.WordRecordDao;
import com.tfjybj.english.provider.dao.WordWrongDao;
import com.tfjybj.english.provider.service.common.HomePageService;
import com.tfjybj.english.provider.service.common.RankService;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class homepageTest  extends TestBaseProject {

    @Autowired
    HomePageService homePageService;

    @Autowired
    WordDao wordDao;

    @Autowired
    WordRecordDao wordRecordDao;

    @Autowired
    WordWrongDao wordWrongDao;

    @Autowired
    RankService rankService;

    @Autowired
    RedisToDbService redisToDbService;

    @Test
    public void testStayWords(){
        String userId = "1";
        HomePageNumsModel homePageNumsModel =  homePageService.stayWords(userId);
        System.out.println(homePageNumsModel.toString());
    }

    @Test
    public void testzMine()
    {
        String userId = "1";
        MineModel str = rankService.Mine(userId);
        System.out.println(str);
    }

    @Test
    public void testWord(){
        /*List<WordEntity> wordEntityList = wordDao.query();
        List<WordRecordEntity> wordRecordEntityList = Lists.newArrayList();
        for(int i = 0;i<300;i++){
            WordRecordEntity wordRecordEntity =  new WordRecordEntity();
            wordRecordEntity.setUserId("1");
            wordRecordEntity.setWordId(wordEntityList.get(i).getId());
            wordRecordEntity.setStatus(0);
            wordRecordEntityList.add(wordRecordEntity);
        }
        long result = wordRecordDao.insertBatch(wordRecordEntityList);

        List<WordWrongEntity> wordWrongEntities = Lists.newArrayList();
        for(int i = 301;i<400;i++){
            WordWrongEntity wordWrongEntity = new WordWrongEntity();
            wordWrongEntity.setWordId(wordEntityList.get(i).getId());
            wordWrongEntity.setUserId("1");
            wordWrongEntity.setIsStudy(0);
            wordWrongEntity.setIsCheck(0);
            wordWrongEntities.add(wordWrongEntity);
        }
        long result1 = wordWrongDao.insertBatch(wordWrongEntities);

        List<WordWrongEntity> wordWrongEntities1 = Lists.newArrayList();
        for(int i = 401;i<501;i++){
            WordWrongEntity wordWrongEntity = new WordWrongEntity();
            wordWrongEntity.setWordId(wordEntityList.get(i).getId());
            wordWrongEntity.setUserId("1");
            wordWrongEntity.setIsStudy(1);
            wordWrongEntity.setIsCheck(0);
            wordWrongEntities1.add(wordWrongEntity);
        }
        long result2 = wordWrongDao.insertBatch(wordWrongEntities1);*/

    }

    @Test
    public void redisLocal(){
        String user = "1";
        List<RankLocalModel> rankLocalModels = rankService.localRankByUserId(user);
        System.out.println(rankLocalModels);
    }

    @Test
    public void addE(){
        boolean flag = rankService.addE("GtLTLszDDfywC2ETtrszEe",1);
        System.out.println(flag);
    }

    @Test
    public void testHome(){
        boolean flag = redisToDbService.StoreDoneToDB("QaYhn2Wq9uvVp6vE1ptizH");
        System.out.println(
                flag
        );
    }

    @Test
    public void testHome1(){
        boolean flag = redisToDbService.StoreCheckDoneToDB("QaYhn2Wq9uvVp6vE1ptizH");
        System.out.println(
                flag
        );
    }
}
