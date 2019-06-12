package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordTestEntity;
import com.tfjybj.english.model.WordTestModel;
import com.tfjybj.english.provider.dao.WordTestDao;
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
public class WordTestServiceImpl extends BaseServicePlusImpl<WordTestDao,WordTestEntity> implements WordTestService {
	
	//region 模板生成
    @Resource
    private WordTestDao wordTestDao;


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
        return wordTestDao.queryAudioByWordId(wordId);
    }

    /**
     *根据单词id匹配对应两个音标
     * @param wordId 单词Id
     * @return 单词Id对应音标
     * @author 张凯超
     */
    @Override
    public List<WordTestEntity> queryPhoneticByWordId(String wordId) {
        return wordTestDao.queryPhoneticByWordId(wordId);
    }

    /**
     * 根据单词拼写查找状态
     * @author
     * @param word 单词
     * @return state 0 正确 1 错误
     */
    @Override
    public WordTestModel queryWordStateByWord(String word) {
        return wordTestDao.queryWordStateByWord(word);
    }
}
