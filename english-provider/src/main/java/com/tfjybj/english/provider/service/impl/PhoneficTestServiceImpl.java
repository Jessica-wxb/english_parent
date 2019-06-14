package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneficEntity;
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

    //音标练习(听)_根据音标id查询对应正确单词_邢美玲
    @Override
    public List<PhoneficTestModel>  getPhoneficTestByIdById(Integer phoneficid){
        return phoneficTestDao.getPhoneficTestById(phoneficid);
    }

    /**
     * 根据用户Id查询音标Id、音频
     * @param userId 用户Id
     * @return 音标Id、音频
     * @since  2019年6月13日22:31:07
     */
    @Override
    public List<PhoneficEntity> queryAudioByUserId(String userId) {
        return phoneficTestDao.queryAudioByUserId(userId);
    }

    /**
     * 根据音标ID在音标测试表获取音标
     * @author 张凯超
     * @param phoneficId 音标Id
     * @return 音标Id对应两个音标
     * @since 2019年6月14日09:37:26
     */
    @Override
    public List<PhoneficTestModel> queryPhoneficByPhoneficId(String phoneficId) {
        return phoneficTestDao.queryPhoneficByPhoneficId(phoneficId);
    }


}
