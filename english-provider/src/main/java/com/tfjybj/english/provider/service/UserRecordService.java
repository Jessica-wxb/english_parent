package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.UserRecordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.UserRecordModel;

import java.util.List;


/**
 * UserRecordService接口
 * userRecord表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface UserRecordService extends BaseServicePlus<UserRecordEntity> {
    //查询该用户id的已学单词--邢美玲
    int findStudyWordById(int userid);

    List<UserRecordModel> queryNoDetectedByUId(String userId);
}
