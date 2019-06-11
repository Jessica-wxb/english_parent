package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.WordDao;
import com.tfjybj.english.provider.service.WordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

/**
 * WordService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("wordService")
public class WordServiceImpl extends BaseServicePlusImpl<WordDao,WordEntity> implements WordService {
	
	//region 模板生成
    @Resource
    private WordDao wordDao;


    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    @Override
    public List<WordModel> selDataNum(Integer setNumber) {
        return wordDao.selDataNum(setNumber);
    }

    @Override
    public Integer selectAll() {
        return wordDao.selectAll();
}


    /**
     *
     * @param  Id 根据Id查找当前显示图片
     * @return Id 返回一个Id值
     * @author 任嘉颖
     * @since 2019年6月10日15:13:41
     */

    @Override
    public List<WordModel> selectPhoneficPictureById( Integer Id) {return wordDao.selectPhoneficPictureById(Id);}
}
