package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.WordRecordEntity;
import com.tfjybj.english.model.RandomWord;
import com.tfjybj.english.model.WordDetection;
import com.tfjybj.english.model.WordTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.tfjybj.english.entity.WordWrongEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * WordRecordDao接口
 * wordRecord表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Repository("wordRecordDao")
public interface WordRecordDao extends BaseMapper<WordRecordEntity> {

    /**
     * 根据id批量插入
     */
    long insertBatch(List<WordRecordEntity> wordRecordEntity);

    /**
     * 批量修改study
     */
    long updateStatus(List<WordRecordEntity> wordRecordEntity);

    /**
     * 查询待检测单词和图片音频
     */
    List<WordDetection> selectWord(String userId);

    /**
     * 随机单词和图片
     */
    List<RandomWord> randomWord(String userId);


    /**
     * 根据userId查询今天单词已学习数量--董可
     * @param userId
     * @return
     */
    int queryLearnNumByuserId(@Param("userId") String userId,@Param("limit") Integer limit,@Param("toDay")String toDay);

    /**
     * 查询待检测单词数量--董可
     * @param userId
     * @return
     */
    int queryToCheckWordsByUserId(@Param("userId") String userId,@Param("limit") Integer limit);

    /**
     * 单词归仓
     * @param userId
     * @param limit
     * @return
     */
    int queryStoreNumsByUserId(@Param("userId") String userId,@Param("limit") Integer limit);


    /**
     * 归仓检测
     * @param userId
     * @param limit
     * @return
     */
    int queryStoreCheckNumsByUserId(@Param("userId") String userId,@Param("limit") Integer limit);

    /**
     * 根据id查询tn_word_template
     * @param wordId
     * @return
     */
    WordTemplate selectWordTemplate(String wordId);

    /**
     * 根据userId查询用户学过的所有单词--董可--2019年8月29日09:07:13
     * @param userId
     * @return
     */
    Integer queryAllWordNumByuserId(String userId);


}
