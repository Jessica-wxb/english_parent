package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserSetEntity;
import org.springframework.stereotype.Repository;

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
	
}
