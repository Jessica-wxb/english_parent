package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordRecordEntity;
import com.tfjybj.english.model.NewPictureAddress;
import com.tfjybj.english.model.UserNewpictureModel;
import com.tfjybj.english.model.UserNewpictureModel;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.model.WordPartModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * WordDao接口
 * word表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Repository("wordDao")
public interface WordDao extends BaseMapper<WordEntity> {

    //查询今天需要学习的单词数量--
    int findWordnumsById(String userId);
    //查询需要学习的单词ID
    List<WordPartModel> findWordIdByWordnums(@Param("userId")String userId, @Param("wordNums")int wordNums);
    //查询学生设置表中设置的学习数量？？？可以替换成伟强的方法
    //int findSetnumsById(String userid);
    //查询所有剩余没学习的单词数量
    int findOtherworsById(String userId);
    //所有单词第一遍学完，在学习记录表中按照时间降序查找所需要的单词ID
    List<WordPartModel> findWordIDByToStudyWords(@Param("userId")String userId,@Param("limit")int limit);
    //根据当天需要学习的单词Id查询新表中的单词ID
    // List<WordEntity> findNewWordById(Map param);
    /**
     * 根据wordId查找属于它的图片
     *
    /**
     * 根据wordId查找属于它的图片
     *
     * @param wordId 单词Id
     * @return 根据wordId查找属于它的图片
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    List<UserNewpictureModel> findWordPicture(String wordId);

    /**
     * 将word表插入Redis
     *
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    List<WordModel> wordInsertRedis();

    /**
     * 通过单词拼写模糊查询单词
     *
     * @param word 单词拼写
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    List<WordModel> findWordByName(String word,String type);
    /**
     * 分页查询所有Word
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 闫伟强
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    List<String> queryPageAll(@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    //插入新图到新图表中---邢美玲--2019年8月18日20:01:01
    void insertNewPicturea(@Param("id")String id,@Param("userId")String userId,@Param("wordId")String wordId,@Param("pictureAddress")String pictureAddress);

    // 查询数据库获取到该用户的设置数量
    UserSetEntity findUserSetById(String userId);

    //首页要用的查询--董可--2019年8月20日21:04:12
    List<WordEntity> query();

    List<WordEntity> findAllWord();

    /**
     * 根据id批量插入
     */
    long insertBatch(List<WordEntity> list);
}
