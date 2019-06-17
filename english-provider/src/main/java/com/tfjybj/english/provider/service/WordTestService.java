package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordPhoneficEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.WordPhoneficModel;

import java.util.List;


/**
 * WordTestService接口
 * wordTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface WordTestService extends BaseServicePlus<WordPhoneficEntity> {

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
    List<WordPhoneficEntity> queryPhoneticByWordId(String wordId);

    /**
     * 根据音标Id拼写查找状态
     * @author
     * @param phoneficId 音标Id
     * @return state 0 正确 1 错误
     */
    WordPhoneficEntity queryWordStateByphoneficId(String phoneficId);


    /**
     * 根据单词Id获取相关音标信息
     * @author 张凯超
     * @param wordId 单词Id
     * @return 音标信息
     * @since 2019年6月14日22点35分
     *
     */
    List<WordPhoneficModel> queryPhoneficAboutByWordId(String wordId);

    /**
     * 根据主键Id查询所有信息
     * @author 张凯超
     * @param id
     * @return
     * @since 2019年6月16日-21点14分
     */
    List<WordPhoneficModel> queryAllById(String id);
}
