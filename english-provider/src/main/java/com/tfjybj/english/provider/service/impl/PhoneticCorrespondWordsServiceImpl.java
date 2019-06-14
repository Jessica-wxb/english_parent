package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneticCorrespondWordsEntity;
import com.tfjybj.english.provider.dao.PhoneticCorrespondWordsDao;
import com.tfjybj.english.provider.service.PhoneticCorrespondWordsService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * PhoneticCorrespondWordsService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version 1.0.0
 * @since 2019-06-14 16:48:08
 */
@Service("phoneticCorrespondWordsService")
public class PhoneticCorrespondWordsServiceImpl extends BaseServicePlusImpl<PhoneticCorrespondWordsDao,PhoneticCorrespondWordsEntity> implements PhoneticCorrespondWordsService {
	
	//region 模板生成
    @Resource
    private PhoneticCorrespondWordsDao phoneticCorrespondWordsDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
