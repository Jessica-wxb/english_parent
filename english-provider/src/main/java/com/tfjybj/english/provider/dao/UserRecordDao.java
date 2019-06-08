package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * UserRecordDao接口
 * userRecord表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("userRecordDao")
public interface UserRecordDao extends BaseMapper<UserRecordEntity> {
	
}
