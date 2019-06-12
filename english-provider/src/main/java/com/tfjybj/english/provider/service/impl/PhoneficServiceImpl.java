package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.provider.dao.PhoneficDao;
import com.tfjybj.english.provider.service.PhoneficService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * PhoneficService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("phoneficService")
public class PhoneficServiceImpl extends BaseServicePlusImpl<PhoneficDao,PhoneficEntity> implements PhoneficService {
	
	//region 模板生成
    @Resource
    private PhoneficDao phoneficDao;


    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据Id获取音标audio
     * @author 张凯超
     * @param id 音标表 主键Id
     * @return 音标对应音频
     * @datetime 2019年6月12日09:31:37
     */
    @Override
    public PhoneficEntity queryAudioById(Integer id) {
        return phoneficDao.queryAudioById(id);
    }
}
