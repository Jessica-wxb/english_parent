package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.UserSetEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.UserSetModel;


/**
 * UserSetService接口
 * userSet表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
public interface UserSetService extends BaseServicePlus<UserSetEntity> {

    /**
     * 通过userid查询用户的设置信息
     * @author 闫伟强
     * @return
     */
    UserSetEntity findUserSetById();

    /**
     * 通过userid修改用户的设置信息
     * @author 闫伟强
     * @return
     */
    UserSetEntity modifyById(Integer palyNums,Integer isTurnAuto, Integer tuenDelayTime, Integer studyNumber);
}
