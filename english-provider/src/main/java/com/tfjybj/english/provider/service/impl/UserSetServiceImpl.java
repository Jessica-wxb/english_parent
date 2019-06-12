package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.model.UserSetModel;
import com.tfjybj.english.provider.dao.UserSetDao;
import com.tfjybj.english.provider.service.UserSetService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserSetService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("userSetService")
public class UserSetServiceImpl extends BaseServicePlusImpl<UserSetDao, UserSetEntity> implements UserSetService {

    //region 模板生成
    @Resource
    private UserSetDao userSetDao;

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    @Override
    public List<UserSetModel> getStudyNumberService(Integer userid) {
        return userSetDao.getStudyNumberService(userid);

    }

    @Override
    public UserSetEntity getByUserId(String userId) {
        return userSetDao.selectByUserId(userId);
    }

    @Override
    public UserSetEntity updateTimesById(String userId, String phoneficNumber) {
        return userSetDao.updateTimesById(userId, phoneficNumber);
    }
}
