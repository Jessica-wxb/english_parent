package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.model.WordModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WordDao接口
 * word表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("wordDao")
public interface WordDao extends BaseMapper<WordEntity> {

    /**
     * 根据设定学习量查询数据条数
     *
     * @param setNumber 设定当天学习任务量
     * @return 查询任务量条数
     * @author 马莹
     * @since 2019-6-9 19:41:00
     */
    List<WordModel> selDataNum(@Param("setNumber") Integer setNumber);

    /**
     * //查询所有word_邢美玲
     *
     * @return :查询到的数
     */
    Integer selectAll();


    /**
     * @param studSum 当天学习任务量
     * @return Id 返回一个Id值
     * @author 任嘉颖
     * @since 2019年6月11日11:14:02
     */
    List<WordModel> selectPhoneficPictureById(@Param("studSum") Integer studSum);

    WordEntity queryStateByWord(@Param("word") String word);

    WordEntity queryPictureByWord(String word);

    /**
     * 无参查询所有word表中的数据
     *
     * @return 所有word表中的实体集合
     * @author 马莹
     * @since 2019-6-12 20:30:00
     */
    List<WordModel> queryWordAll();

    /*
     * 根据单词Id查询单词音频
     * @author 张凯超
     * @param wordId 单词Id
     * @return 单词音频
     */
    WordModel queryAudioBywordId( @Param("wordId") String wordId);

    /**
     *  根据音标id查询图片
     * @author 张凯超
     * @param phoneficId 音标Id
     * @since 2019年6月13日22:39:16
     * @return 图片
     */
    List<WordModel> queryPictureByPhoneficId(@Param("phoneficId") String phoneficId);

    /**
     * 根据用户ID获取用户记录中单词、单词Id
     * @param userId 用户Id
     * @return 单词、单词Id
     * @since 2019年6月14日21:24:13
     */
    List<WordModel> queryWordAboutByUserId(@Param("userId") String userId);
}
