package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneficTestEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.PhoneficTestModel;

import java.util.List;


/**
 * PhoneficTestService接口
 * phoneficTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface PhoneficTestService extends BaseServicePlus<PhoneficTestEntity> {

    List<PhoneficTestModel> queryAudioByPhoneficId(String phoneficId);

    List<PhoneficTestModel> selectWordByPhoneficId(String phoneficId);

    PhoneficTestEntity queryStateByWord(String word);
}
