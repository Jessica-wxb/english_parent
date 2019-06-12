package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserWrongEntity;
import com.tfjybj.english.model.UserWrongModel;
import com.tfjybj.english.model.WordModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserWrongDao接口
 * userWrong表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("userWrongDao")
public interface UserWrongDao extends BaseMapper<UserWrongEntity> {
    /**
     * 根据用户id查询该用户的错误单词
     * @author 陈广晗
     * @since 2019-06-11 15:43:38
     * @param id  该用户的id
     * @return 该用户的错误记录
     */
    List<UserWrongModel> queryWrongWordId(@Param("userId") Integer userId);

    /**
     * 根据用户id查询该用户的错误音标
     * @author 陈广晗
     * @since 2019-06-11 15:43:38
     * @param id  该用户的id
     * @return 该用户的错误记录
     */
    List<UserWrongModel> queryWrongPhoneficId(@Param("userId") Integer userId);
}
