package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.UserInfoEntity;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.service.UserInfoService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * UserInfoService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServicePlusImpl<UserInfoDao,UserInfoEntity> implements UserInfoService {
	
	//region 模板生成
    @Resource
    private UserInfoDao userInfoDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
