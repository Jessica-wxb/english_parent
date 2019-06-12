package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.model.UserSetModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserSetDao接口
 * userSet表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("userSetDao")
public interface UserSetDao extends BaseMapper<UserSetEntity> {

    /**
     * 根据用户id，查询用户设置
     *
     * @param userid 用户id
     * @return 返回实体集合
     * @since 2019年6月11日18:13:01
     */
    List<UserSetModel> getStudyNumberService(@Param("userid") Integer userid);

    UserSetEntity selectByUserId(String userId);

    UserSetEntity updateTimesById(@Param("userId") String userId, @Param("phoneficNumber") String phoneficNumber);
}
