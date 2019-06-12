package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneficEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;


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
}
