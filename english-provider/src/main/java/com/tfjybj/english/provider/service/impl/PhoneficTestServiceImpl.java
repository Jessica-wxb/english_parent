package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneficTestEntity;
import com.tfjybj.english.model.PhoneficTestModel;
import com.tfjybj.english.provider.dao.PhoneficTestDao;
import com.tfjybj.english.provider.service.PhoneficTestService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * PhoneficTestService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("phoneficTestService")
public class PhoneficTestServiceImpl extends BaseServicePlusImpl<PhoneficTestDao,PhoneficTestEntity> implements PhoneficTestService {
	
	//region 模板生成
    @Resource
    private PhoneficTestDao phoneficTestDao;

    @Override
    public List<PhoneficTestModel> queryAudioByPhoneficId(String phoneficId) {

        return phoneficTestDao.queryAudioByPhoneficId(phoneficId);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    // 根据音标id匹配对应两个单词word-薛帅行-2019年6月11日15:47:59
    @Override
    public List<PhoneficTestModel> selectWordByPhoneficId(String phoneficId) {
        return phoneficTestDao.selectWordByPhoneficId(phoneficId);
    }

    @Override
    public PhoneficTestEntity queryStateByWord(String word) {
        return phoneficTestDao.queryStateByWord(word);
    }
}
