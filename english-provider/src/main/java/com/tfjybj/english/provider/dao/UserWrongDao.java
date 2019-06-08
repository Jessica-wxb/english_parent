package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserWrongEntity;
import org.springframework.stereotype.Repository;

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
	
}
