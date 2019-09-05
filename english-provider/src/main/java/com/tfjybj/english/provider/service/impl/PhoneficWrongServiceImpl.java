package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneficWrongEntity;
import com.tfjybj.english.provider.dao.PhoneficWrongDao;
import com.tfjybj.english.provider.service.PhoneficWrongService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * PhoneficWrongService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("phoneficWrongService")
public class PhoneficWrongServiceImpl extends BaseServicePlusImpl<PhoneficWrongDao,PhoneficWrongEntity> implements PhoneficWrongService {
	
	//region 模板生成
    @Resource
    private PhoneficWrongDao phoneficWrongDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
