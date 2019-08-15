package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneticCorrespondWordsEntity;
import com.tfjybj.english.model.PhoneticCorrespondWordsModel;
import com.tfjybj.english.provider.dao.PhoneticCorrespondWordsDao;
import com.tfjybj.english.provider.service.PhoneticCorrespondWordsService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 根据音标ID查询对应的单词
     * @return 音标所对应的单词
     * @param id 音标id
     * @author 闫伟强
     * @since ${version} 2019年6月15日19:17:28
     */
    @Override
    public List<PhoneticCorrespondWordsModel> findWordById(String id){
        return phoneticCorrespondWordsDao.findWordById(id);
    }

}
