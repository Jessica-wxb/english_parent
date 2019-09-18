package com.tfjybj.english.provider.service;

import com.tfjybj.english.model.PhoneticCheckModel;
import com.tfjybj.english.entity.PhoneticRecordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;


/**
 * PhoneticRecordService接口
 * phoneticRecord表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
public interface PhoneticRecordService extends BaseServicePlus<PhoneticRecordEntity> {
	    /**
     * @param
     * @return 进入音标检测
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    PhoneticCheckModel findPhoneticCheck();

    PhoneticCheckModel nextPhonetic(String phonetic, Integer correct, Integer falseType);
}
