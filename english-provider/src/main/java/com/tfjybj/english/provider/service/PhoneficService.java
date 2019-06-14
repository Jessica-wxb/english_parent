package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneficEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.PhoneficModel;

import java.util.List;


/**
 * PhoneficService接口
 * phonefic表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface PhoneficService extends BaseServicePlus<PhoneficEntity> {

    PhoneficEntity selectAudioByPhonefic(String phonefic);

    /**
     * 通过音标Id查找对应图片
     * @Author 张凯超
     * @param phoneficId 音标
     * @return 音标对应图片
     */
    List<PhoneficEntity> queryPictureByPhonefic(String phoneficId);
    // 音标练习(看)-查询 的所有图片等_xml
    List<PhoneficModel> getPhoneficById(Integer id);
}
