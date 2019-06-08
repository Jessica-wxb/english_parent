package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.UserWrongEntity;
import com.tfjybj.english.provider.dao.UserWrongDao;
import com.tfjybj.english.provider.service.UserWrongService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * UserWrongService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("userWrongService")
public class UserWrongServiceImpl extends BaseServicePlusImpl<UserWrongDao,UserWrongEntity> implements UserWrongService {
	
	//region 模板生成
    @Resource
    private UserWrongDao userWrongDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
