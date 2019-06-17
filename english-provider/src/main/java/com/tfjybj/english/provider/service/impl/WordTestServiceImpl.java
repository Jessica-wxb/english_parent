package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordPhoneficEntity;
import com.tfjybj.english.model.WordPhoneficModel;
import com.tfjybj.english.provider.dao.WordPhoneficDao;
import com.tfjybj.english.provider.service.WordTestService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * WordTestService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("wordTestService")
public class WordTestServiceImpl extends BaseServicePlusImpl<WordPhoneficDao,WordPhoneficEntity> implements WordTestService {
	
	//region 模板生成
    @Resource
    private WordPhoneficDao wordPhoneficDao;


    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据单词Id获取对应单词audio
     * @param wordId 单词Id
     * @return 单词audio
     * @author 张凯超
     */
    @Override
    public WordEntity queryAudioByWordId(String wordId) {
        return wordPhoneficDao.queryAudioByWordId(wordId);
    }

    /**
     *根据单词id匹配对应两个音标
     * @param wordId 单词Id
     * @return 单词Id对应音标
     * @author 张凯超
     */
    @Override
    public List<WordPhoneficEntity> queryPhoneticByWordId(String wordId) {
        return wordPhoneficDao.queryPhoneticByWordId(wordId);
    }

    /**
     * 根据音标Id拼写查找状态
     * @author
     * @param phoneficId 单词
     * @return state 0 正确 1 错误
     */
    @Override
    public WordPhoneficEntity queryWordStateByphoneficId(String phoneficId) {
        return wordPhoneficDao.queryWordStateByphoneficId(phoneficId);
    }

    /**
     * 根据单词Id获取相关音标信息
     * @author 张凯超
     * @param wordId 单词Id
     * @return 音标信息
     * @since 2019年6月14日22点35分
     *
     */
    @Override
    public List<WordPhoneficModel> queryPhoneficAboutByWordId(String wordId) {
        return wordPhoneficDao.queryPhoneficAboutByWordId(wordId);
    }

    /**
     * 根据主键Id查询所有信息
     * @author 张凯超
     * @param id
     * @return
     * @since 2019年6月16日-21点14分
     */
    @Override
    public List<WordPhoneficModel> queryAllById(String id) {
        return wordPhoneficDao.queryAllById(id);
    }
}
