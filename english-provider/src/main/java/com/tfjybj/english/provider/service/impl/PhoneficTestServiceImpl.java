package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneficTestEntity;
import com.tfjybj.english.model.PhoneficTestModel;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.model.WordTestModel;
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


    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据音标Id获取对应单词的单词Id、音频audio、单词图片picture
     * @author 张凯超
     * @param phoneficId 音标测试表音标Id
     * @return 单词的单词Id、音频audio、单词图片picture
     * @datetime 2019年6月12日10:04:17
     */
    @Override
    public List<WordModel> queryWordIdAudioPicByPhoneficId(String phoneficId) {
        return phoneficTestDao.queryWordIdAudioPicByPhoneficId(phoneficId);
    }
}
