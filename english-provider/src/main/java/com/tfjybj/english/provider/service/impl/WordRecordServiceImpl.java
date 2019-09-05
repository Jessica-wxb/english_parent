package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordRecordEntity;
import com.tfjybj.english.provider.dao.WordRecordDao;
import com.tfjybj.english.provider.service.WordRecordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * WordRecordService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("wordRecordService")
public class WordRecordServiceImpl extends BaseServicePlusImpl<WordRecordDao,WordRecordEntity> implements WordRecordService {
	
	//region 模板生成
    @Resource
    private WordRecordDao wordRecordDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
