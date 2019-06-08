package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.UserRecordEntity;
import com.tfjybj.english.provider.dao.UserRecordDao;
import com.tfjybj.english.provider.service.UserRecordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * UserRecordService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("userRecordService")
public class UserRecordServiceImpl extends BaseServicePlusImpl<UserRecordDao,UserRecordEntity> implements UserRecordService {
	
	//region 模板生成
    @Resource
    private UserRecordDao userRecordDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
