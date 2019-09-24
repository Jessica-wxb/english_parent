package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.model.UserSetModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * UserSetDao接口
 * userSet表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Repository("userSetDao")
public interface UserSetDao extends BaseMapper<UserSetEntity> {

    /**
     * 通过userid查询用户的设置信息
     * @author 闫伟强
     * @return
     */
    UserSetEntity findUserSetById(String userId);

    /**
     * 通过userid修改用户的设置信息
     * @author 闫伟强
     * @return
     */
    void modifyById(@Param("userId")String userId,@Param("palyNums") Integer palyNums,@Param("isTurnAuto") Integer isTurnAuto,@Param("tuenDelayTime") Integer tuenDelayTime,@Param("studyNumber") Integer studyNumber,@Param("isShowWord") Integer isShowWord);

    //查询用户设置的 学单词数量--董可
    int queryStudyNumByUserId(String userId);

}
