package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.AllusersEntity;
import com.tfjybj.english.provider.dao.AllusersDao;
import com.tfjybj.english.provider.service.AllusersService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * AllusersService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:52:28
 */
@Service("allusersService")
public class AllusersServiceImpl extends BaseServicePlusImpl<AllusersDao,AllusersEntity> implements AllusersService {
	
	//region 模板生成
    @Resource
    private AllusersDao allusersDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
