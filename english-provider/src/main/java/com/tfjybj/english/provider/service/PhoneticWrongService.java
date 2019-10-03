package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneticWrongEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.PhoneticStoreModel;
import com.tfjybj.english.model.PhoneticWrongModel;


/**
 * PhoneticWrongService接口
 * phoneticWrong表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
public interface PhoneticWrongService extends BaseServicePlus<PhoneticWrongEntity> {
    /**
     * 音标归仓初始化
     * @author 陈广晗
     * @param userId
     * @return
     */
    PhoneticStoreModel Initialisation(String userId);

    PhoneticStoreModel Modity(PhoneticWrongModel phoneticWrongModel);


}
