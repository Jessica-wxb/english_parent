package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.WordWrongEntity;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.model.WordWrongModel;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WordWrongDao接口
 * wordWrong表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Repository("wordWrongDao")
public interface WordWrongDao extends BaseMapper<WordWrongEntity> {

    /**
     * 根据id批量插入
     */
    long insertBatch(List<WordWrongEntity> userWrongEntity);

    /**
     * 批量修改study
     */
    long updateStudy(List<WordWrongEntity> userWrongEntity);

    /**
     * 批量修改study
     */
    long updateCheck(List<WordWrongEntity> userWrongEntity);


    /**
     * 根据用户Id 单词Id 更新用户学习状态
     * @param userId
     * @param wordId
     * @return
     */
   boolean updateStudyByUserId(@Param("userId") String userId , @Param("wordId") String wordId);

   /**
     * 根据用户Id 单词Id 更新用户学习状态
     * @param userId
     * @param wordId
     * @return
     */
    boolean updateCheckByUserId(@Param("userId") String userId , @Param("wordId") String wordId);

    /**
     *     查询用户待检测数据
     */
    List<WordModel>  queryCheckByUserId(@Param("userId") String userId, @Param("num") Integer num);

    /**
     *     查询用户待学习数据
     */
    List<WordModel> queryStudyByUserId(@Param("userId") String userId);

    /**
     * 获取用户待检测单词数量
     * @param userId
     * @return
     */
    Integer queryNumCheck(String userId);

    /**
     * 获取用户待学习单词数量
     * @param userId
     * @return
     */
    Integer queryNumStudy(@Param("userId") String userId);

    /**
     * 获取用户单词检测正确数量
     * @param userId
     * @return
     */
    Integer queryNumRecord(@Param("userId") String userId);

}
