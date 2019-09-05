package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneficRecordEntity;
import com.tfjybj.english.provider.dao.PhoneficRecordDao;
import com.tfjybj.english.provider.service.PhoneficRecordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * PhoneficRecordService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("phoneficRecordService")
public class PhoneficRecordServiceImpl extends BaseServicePlusImpl<PhoneficRecordDao,PhoneficRecordEntity> implements PhoneficRecordService {
	
	//region 模板生成
    @Resource
    private PhoneficRecordDao phoneficRecordDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
