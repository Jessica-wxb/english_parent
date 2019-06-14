package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneficWordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.PhoneficWordModel;

import java.util.List;


/**
 * PhoneficTestService接口
 * phoneficTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface PhoneficTestService extends BaseServicePlus<PhoneficWordEntity> {

    List<PhoneficWordModel> queryAudioByPhoneficId(String phoneficId);

    List<PhoneficWordModel> selectWordByPhoneficId(String phoneficId);

    PhoneficWordEntity queryStateByWord(String word);

    //音标练习(听)_根据音标id查询对应正确单词_邢美玲
    List<PhoneficWordModel> getPhoneficTestByIdById(Integer phoneficid);
}
