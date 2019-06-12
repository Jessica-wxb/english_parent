package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordTestEntity;
import com.tfjybj.english.provider.dao.WordTestDao;
import com.tfjybj.english.provider.service.WordTestService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * WordTestService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("wordTestService")
public class WordTestServiceImpl extends BaseServicePlusImpl<WordTestDao,WordTestEntity> implements WordTestService {
	
	//region 模板生成
    @Resource
    private WordTestDao wordTestDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
