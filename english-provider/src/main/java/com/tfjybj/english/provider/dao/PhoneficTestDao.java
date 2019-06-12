package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneficTestEntity;
import com.tfjybj.english.model.PhoneficTestModel;
import com.tfjybj.english.model.WordModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PhoneficTestDao接口
 * phoneficTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("phoneficTestDao")
public interface PhoneficTestDao extends BaseMapper<PhoneficTestEntity> {

    /**
     * 根据音标Id获取对应单词的单词Id、音频audio、单词图片picture
     * @author 张凯超
     * @param phoneficId 音标测试表音标Id
     * @return 单词的单词Id、音频audio、单词图片picture
     * @datetime 2019年6月12日10:04:17
     */
    List<WordModel> queryWordIdAudioPicByPhoneficId(@Param("phoneficId") String phoneficId);
}
