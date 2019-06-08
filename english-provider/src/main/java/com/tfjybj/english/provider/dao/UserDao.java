package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * UserDao接口
 * user表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("userDao")
public interface UserDao extends BaseMapper<UserEntity> {
	
}
