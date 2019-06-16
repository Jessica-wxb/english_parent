package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.PhoneficWordModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    //音标练习(看)-根据id查询所有图片等_xml
    List<PhoneficModel> getPhoneficById(Integer id);
    /**
     * 查询所有音标
     * @Author 闫伟强
     * @return 所有音标
     */
    List<PhoneficModel> getPhonefic();
}
