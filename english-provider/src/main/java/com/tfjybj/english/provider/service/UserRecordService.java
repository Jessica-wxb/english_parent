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
    Integer findStudyWordById(String userId);

    List<UserRecordModel> queryNoDetectedByUId(String userId);


    /**
     * 根据用户Id和当天时间,查询音标的ID
     * @param userId 用户ID
     * @return 当天学习过的音标ID(phonefic_id)
     * @since 2019年6月15日11:55:03
     * @autor 冯佳兴
     */
    List<UserRecordModel> selectPhoneficIdByUserIdAndcreatetime(String userId);

    /**
     * 根据用户id和音标id查询该音标今天是否学习过
     * *@param userid 用户id
     * *@param phoneficId 音标id
     * @return true or false
     * @author 闫伟强
     * @since ${version} 2019年6月15日20:23:12
     */
    List<UserRecordModel> queryPhoneficByUIdAndPId(String userId, String phoneficId);

    /**
     * 根据用户ID查询当天音标学习记录
     * @param userId
     * @return
     * @author 张凯超
     */
    List<UserRecordModel> queryphoneficIdByuserId(String userId);
}
