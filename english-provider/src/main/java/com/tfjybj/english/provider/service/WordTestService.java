package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordPhoneficEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.WordPhoneficModel;

import java.util.List;
import java.util.Map;


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
     *
     * @param wordId 单词Id
     * @return 单词audio
     * @author 张凯超
     */
    WordEntity queryAudioByWordId(String wordId);

    /**
     * 根据单词id匹配对应两个音标
     *
     * @param wordId 单词Id
     * @return 单词Id对应音标
     * @author 张凯超
     */
    List<WordPhoneficEntity> queryPhoneticByWordId(String wordId);

    /**
     * 根据音标Id拼写查找状态
     *
     * @param phoneficId 音标Id
     * @return state 0 正确 1 错误
     * @author
     */
    WordPhoneficEntity queryWordStateByphoneficId(String phoneficId);


    /**
     * 根据音标Id获取对应Id的所有信息
     *
     * @param phoneficTrueId 音标Id
     * @return 音标信息
     * @author 张凯超
     * @since 2019年6月14日22点35分
     */
    List<WordPhoneficModel> queryPhoneficAboutByPhoneficTrueId(String phoneficTrueId);

    /**
     * 根据主键Id查询所有信息
     * @author 张凯超
     * @param phoneficid
     * @return
     * @since 2019年6月16日-21点14分
     */
    List<WordPhoneficModel> queryAllById(String phoneficid);

    /**
     * 根据路径插入根据单词选音标的文件路径
     *
     * @param phoneWordPath    根据单词选音标的文件路径
     * @return true/false
     * @author 马莹
     * @since 2019-6-17 21:15:11
     */
    boolean insertPhoneWordTable(String phoneWordPath, Map<String, PhoneficModel> phoneficWordMap);
    /**
     * 根据单词word模糊查询单词音标对应表记录
     * @author 白靖
     * @param word
     * @return
     * @since 2019年6月26日08:43:29
     */

    List<WordPhoneficEntity> queryLikeWordTest(String word);
}
