//package com.tfjybj.demo;
//
//import com.tfjybj.english.entity.WordEntity;
//import com.tfjybj.english.provider.dao.WordDao;
//import com.tfjybj.english.provider.service.WordService;
//import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
//import org.junit.Test;
//import org.springframework.stereotype.Service;
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * WordService接口实现类
// * ${base}表
// *
// * @author 张凯超
// * @version 1.0.0
// * @since 2019-08-16 08:47:57
// */
//@Service("wordService")
//public class WordServiceImpl extends BaseServicePlusImpl<WordDao,WordEntity> implements WordService {
//
//    //region 模板生成
//    @Resource
//    private WordDao wordDao;
//
//    @Override
//    public  List<WordEntity> findWordnumsById(String userid) {
//        //设置的学习数量
//        int setnums = wordDao.findSetnumsById(userid);
//        //当天还需要的学习数量
//        int wordnums = wordDao.findWordnumsById(userid);
//        if (wordnums > 0 ){
//            // otherwords剩余总未学习的单词数量
//            int otherwords = wordDao.findOtherworsById(userid);
//            List<WordEntity> listWordId=new ArrayList<>();
//            List<WordEntity> listAllStudyWordID= new ArrayList<>();
//            List<WordEntity> listStudyWordID = new ArrayList<>();
//            List<WordEntity> listOldStudyWordID = new ArrayList<>();
//            Map param = new HashMap<>();
//            //如果未学习的数量>当天需要学习的数量
//            if( otherwords  >= wordnums ) {
//                // 取出还需要学习的单词
//                listWordId = wordDao.findWordIdByWordnums(wordnums);
//                //listAllStudyWordID.addAll(listWordId);
//                param.put("listWordId",listWordId);
//                List<WordEntity> newordscount = wordDao.findNewWordById(param);
//            }else{
//                //取出历史学习过的单词ID
//                int tostudywords =  wordnums-otherwords;
//                listStudyWordID = wordDao.findWordIdByWordnums(otherwords);
//                listOldStudyWordID = wordDao.findWordIDByToStudyWords(tostudywords);
//                //listAllStudyWordID.addAll(listStudyWordID);
//                // listAllStudyWordID.addAll(listOldStudyWordID);
//                param.put("listStudyWordID",listStudyWordID);
//                param.put("listOldStudyWordID",listOldStudyWordID);
//            }
//        }else{
//            wordnums = setnums;
//            //  List<WordEntity> listWordId = wordDao.findWordIdByWordnums(wordnums);
//        }
//
//
////        // otherwords剩余总未学习的单词数量
////        int otherwords = wordDao.findOtherworsById(userid);
////        List<WordEntity> listWordId=new ArrayList<>();
////        List<WordEntity> listAllStudyWordID= new ArrayList<>();
////        List<WordEntity> listStudyWordID = new ArrayList<>();
////        List<WordEntity> listOldStudyWordID = new ArrayList<>();
////        Map param = new HashMap<>();
////        //如果未学习的数量>当天需要学习的数量
////        if( otherwords  >= wordnums ) {
////            // 取出还需要学习的单词
////             listWordId = wordDao.findWordIdByWordnums(wordnums);
////            //listAllStudyWordID.addAll(listWordId);
////            param.put("listWordId",listWordId);
////            List<WordEntity> newordscount = wordDao.findNewWordById(param);
////        }else{
////            //取出历史学习过的单词ID
////            int tostudywords =  wordnums-otherwords;
////            listStudyWordID = wordDao.findWordIdByWordnums(otherwords);
////            listOldStudyWordID = wordDao.findWordIDByToStudyWords(tostudywords);
////            //listAllStudyWordID.addAll(listStudyWordID);
////           // listAllStudyWordID.addAll(listOldStudyWordID);
////            param.put("listStudyWordID",listStudyWordID);
////            param.put("listOldStudyWordID",listOldStudyWordID);
////            }
//        return wordnums;
//
//
//    }
//
//    //endregion
//
//    /* **********************************以下为非模板生成的内容********************************* */
//}
