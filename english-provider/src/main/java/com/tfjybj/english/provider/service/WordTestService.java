package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordTestEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.WordTestModel;

import java.util.List;


/**
 * WordTestService接口
 * wordTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface WordTestService extends BaseServicePlus<WordTestEntity> {

    /**
     * 根据单词Id获取对应单词audio
     * @param wordId 单词Id
     * @return 单词audio
     * @author 张凯超
     */
    WordEntity queryAudioByWordId(String wordId);

    /**
     *根据单词id匹配对应两个音标
     * @param wordId 单词Id
     * @return 单词Id对应音标
     * @author 张凯超
     */
    List<WordTestEntity> queryPhoneticByWordId(String wordId);

    /**
     * 根据单词拼写查找状态
     * @author
     * @param word 单词
     * @return state 0 正确 1 错误
     */
    WordTestModel queryWordStateByWord(String word);
}
