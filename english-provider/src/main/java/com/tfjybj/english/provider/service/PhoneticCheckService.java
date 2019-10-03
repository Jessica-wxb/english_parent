package com.tfjybj.english.provider.service;

import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.entity.PhoneticWordEntity;
import com.tfjybj.english.model.PhoneticCheckModel;
import org.springframework.stereotype.Service;

@Service
public interface PhoneticCheckService extends BaseServicePlus<PhoneticWordEntity> {
    /**
     * @param
     * @return 进入音标检测
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    PhoneticCheckModel findPhoneticCheck();

    PhoneticCheckModel nextPhonetic(String phonetic, Integer correct, Integer falseType);
}
