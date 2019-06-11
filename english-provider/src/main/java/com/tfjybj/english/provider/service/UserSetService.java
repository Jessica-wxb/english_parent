package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.UserSetEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.UserSetModel;

import java.util.List;


/**
 * UserSetService接口
 * userSet表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface UserSetService extends BaseServicePlus<UserSetEntity> {

    // 根据用户id，查询用户设置——白爱民2019年6月11日18:13:01
    List<UserSetModel> getStudyNumberService(Integer userid);
}
