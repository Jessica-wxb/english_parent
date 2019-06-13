package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.UserEntity;
import com.tfjybj.english.model.UserModel;
import com.tfjybj.english.provider.dao.UserDao;
import com.tfjybj.english.provider.service.UserService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * UserService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("userService")
public class UserServiceImpl extends BaseServicePlusImpl<UserDao,UserEntity> implements UserService {
	
	//region 模板生成
    @Resource
    private UserDao userDao;


    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    @Override
    public UserModel queryUserByUserId(String userId) {
        return userDao.queryUserByUserId(userId);
    }
}
