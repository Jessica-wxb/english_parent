package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneticCorrespondWordsEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.PhoneticCorrespondWordsModel;

import java.util.List;


/**
 * PhoneticCorrespondWordsService接口
 * phoneticCorrespondWords表
 *
 * @author 马莹
 * @version 1.0.0
 * @since 2019-06-14 16:48:08
 */
public interface PhoneticCorrespondWordsService extends BaseServicePlus<PhoneticCorrespondWordsEntity> {

    /**
     * 根据音标ID查询对应的单词
     * @return 音标所对应的单词
     * @param id 音标id
     * @author 闫伟强
     * @since ${version} 2019年6月15日19:17:28
     */
    List<PhoneticCorrespondWordsModel> findWordById(String id);
}
