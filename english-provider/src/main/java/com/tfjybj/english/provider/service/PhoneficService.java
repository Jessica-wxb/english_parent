package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneficEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.PhoneficModel;

import java.io.IOException;
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
     * @param phoneficId 音标
     * @return 音标对应图片
     * @Author 张凯超
     */
    List<PhoneficEntity> queryPictureByPhonefic(String phoneficId);

    // 音标练习(看)-查询 的所有图片等_xml
    List<PhoneficModel> getPhoneficById(Integer id);

    /**
     * 根据目录结构插入音标
     *
     * @param phonePath 文件所在的路径
     * @return true/false
     * @author 马莹
     * @since 2019-6-14 10:19:54
     */
    boolean phonePathInstert(String phonePath) throws IOException;

    /**
     * 查询所有音标
     * @Author 闫伟强
     * @return 所有音标
     */
    List<PhoneficModel> getPhonefic();
    /**
     * 根据音标模糊查询
     * @Author 白靖
     * @return 音标模糊查询结果
     */
    List<PhoneficEntity> queryLikePhonefic(String  phonefic);
}
