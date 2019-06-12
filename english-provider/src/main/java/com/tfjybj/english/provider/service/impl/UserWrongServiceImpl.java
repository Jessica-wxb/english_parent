package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.UserWrongEntity;
import com.tfjybj.english.model.UserWrongModel;
import com.tfjybj.english.provider.dao.UserWrongDao;
import com.tfjybj.english.provider.service.UserWrongService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * UserWrongService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("userWrongService")
public class UserWrongServiceImpl extends BaseServicePlusImpl<UserWrongDao,UserWrongEntity> implements UserWrongService {
	
	//region 模板生成
    @Resource
    private UserWrongDao userWrongDao;
	
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据用户id查询错误的单词
     * @author 陈广晗
     * @since 2019-06-12 21:42:10
     * @param userId  该用户的id
     * @return 用户错题表的信息
     */
    @Override
    public List<UserWrongModel> queryWrongWordId(Integer userId) {
        return userWrongDao.queryWrongWordId(userId);
    }
    /**
     * 根据用户id查询错误的音标
     * @author 陈广晗
     * @since 2019-06-12 21:42:10
     * @param userId  该用户的id
     * @return 用户错题表的信息
     */
    @Override
    public List<UserWrongModel> queryWrongPhoneficId(Integer userId) {
        return userWrongDao.queryWrongPhoneficId(userId);
    }
}
