package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.UserEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.UserModel;


/**
 * UserService接口
 * user表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface UserService extends BaseServicePlus<UserEntity> {

    //根据用户id查询用户-张伟杰-2019-6-13 11:58:12
    UserModel queryUserByUserId(String userId);
}
