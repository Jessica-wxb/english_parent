package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.UserWrongEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.UserWrongModel;
import com.tfjybj.english.model.WordModel;

import java.util.List;


/**
 * UserWrongService接口
 * userWrong表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface UserWrongService extends BaseServicePlus<UserWrongEntity> {
    /**
     * @author 陈广晗
     * @since 2019-06-11 15:43:38
     * @param userId  该用户的id
     * @return 该用户的错误记录
     */
    List<UserWrongModel> queryWrongWordId(String userId);

    /**
     * @author 陈广晗
     * @since 2019-06-11 15:43:38
     * @param userId  该用户的id
     * @return 该用户的错误记录
     */
    List<UserWrongModel> queryWrongPhoneficId(String userId);



}
