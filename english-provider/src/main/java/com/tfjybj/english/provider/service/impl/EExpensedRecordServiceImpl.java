package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.EExpensedRecordEntity;
import com.tfjybj.english.provider.dao.EExpensedRecordDao;
import com.tfjybj.english.provider.service.EExpensedRecordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * EExpensedRecordService接口实现类
 * ${base}表
 *
 * @author 王小波
 * @version ${version}
 * @since ${version} 2019-09-20 16:05:03
 */
@Service("eExpensedRecordService")
public class EExpensedRecordServiceImpl extends BaseServicePlusImpl<EExpensedRecordDao,EExpensedRecordEntity> implements EExpensedRecordService {
	
	//region 模板生成
    @Resource
    private EExpensedRecordDao eExpensedRecordDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */

}
