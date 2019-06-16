package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.UserRecordEntity;
import com.tfjybj.english.model.UserRecordModel;
import com.tfjybj.english.provider.dao.UserRecordDao;
import com.tfjybj.english.provider.service.UserRecordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * UserRecordService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("userRecordService")
public class UserRecordServiceImpl extends BaseServicePlusImpl<UserRecordDao,UserRecordEntity> implements UserRecordService {
	
	//region 模板生成
    @Resource
    private UserRecordDao userRecordDao;

	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
    /**
     * WordService根据userid查wordid数接口实现类
     * ${base}表
     * @author 邢美玲
     * @version ${version}
     * @since ${version} 2019年6月9日15:47:16
     */
    public int findStudyWordById(int userid){
        return userRecordDao.findStudyWordById(userid);
    }

    /**
     * 根据用户Id查询未检测的单词
     * @param userId
     * @return 单词list
     * @author 张伟杰
     * @since 2019年6月13日23:26:47
     */
    @Override
    public List<UserRecordModel> queryNoDetectedByUId(String userId) {
        return userRecordDao.queryNoDetectedByUId(userId);
    }

}
