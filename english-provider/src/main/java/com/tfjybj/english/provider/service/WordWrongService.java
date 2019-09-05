package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.WordWrongEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.WordWrongModel;
import io.lettuce.core.output.BooleanListOutput;

import java.util.List;


/**
 * WordWrongService接口
 * wordWrong表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
public interface WordWrongService extends BaseServicePlus<WordWrongEntity> {

    /**查询用户未学习数据
     * @param userId
     * @return
     */
    List<WordWrongModel> queryWrongByUserId(String userId);

    /**
     * 查询用户已学习未检测数据
     */
    List<WordWrongModel> queryCheckByUserId(String userId);

    /**
     * 根据用户Id更新用户学习状态
     * @param userId 用户Id
     * @param  wordId  单词Id
     * @return
     */
    public boolean updateStudyByUserId(String userId,String wordId);

    /**
     * 根据用户Id更新用户单词检测状态
     * @param userId
     * @param wordId
     * @return
     */
   public boolean updateCheckByUserId(String userId, String wordId);

}
