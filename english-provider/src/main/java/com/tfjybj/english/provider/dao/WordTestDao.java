package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordTestEntity;
import com.tfjybj.english.model.WordTestModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WordTestDao接口
 * wordTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("wordTestDao")
public interface WordTestDao extends BaseMapper<WordTestEntity> {

    /**
     * 根据单词Id获取对应单词audio
     * @param wordId 单词Id
     * @return 单词audio
     * @author 张凯超
     */
    WordEntity queryAudioByWordId(@Param("wordId") String wordId);

    /**
     * 根据单词Id获取对应音标
     * @param wordId 单词Id
     * @return 单词audio
     * @author 张凯超
     */
    List<WordTestEntity> queryPhoneticByWordId(@Param("wordId") String wordId);

    /**
     * 根据音标Id拼写查找状态
     * @author
     * @param phoneficId 单词
     * @return state 0 正确 1 错误
     */
    WordTestEntity queryWordStateByphoneficId (@Param("phoneficId") String phoneficId);

}
