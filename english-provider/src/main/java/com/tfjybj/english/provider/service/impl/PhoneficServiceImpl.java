package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.provider.dao.PhoneficDao;
import com.tfjybj.english.provider.service.PhoneficService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PhoneficService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("phoneficService")
public class PhoneficServiceImpl extends BaseServicePlusImpl<PhoneficDao, PhoneficEntity> implements PhoneficService {

    //region 模板生成
    @Resource
    private PhoneficDao phoneficDao;

    // 音标练习(看)-根据id查询所有图片等_xml
    @Override
    public List<PhoneficModel> getPhoneficById(Integer id) {

        return phoneficDao.getPhoneficById(id);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

}
