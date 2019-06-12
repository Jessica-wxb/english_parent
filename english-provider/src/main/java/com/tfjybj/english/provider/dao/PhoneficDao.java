package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.model.PhoneficTestModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * PhoneficDao接口
 * phonefic表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("phoneficDao")
public interface PhoneficDao extends BaseMapper<PhoneficEntity> {

    PhoneficEntity selectAudioByPhonefic(String phonefic);

    /**
     * 通过音标拼写查找对应图片
     * @Author 张凯超
     * @param phonetic 音标
     * @return 音标对应图片
     */
    PhoneficTestModel queryPictureByPhonetic(@Param("phonetic") String phonetic);
}
