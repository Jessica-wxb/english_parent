package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.AllusersEntity;
import org.springframework.stereotype.Repository;

/**
 * AllusersDao接口
 * allusers表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 17:38:43
 */
@Repository("allusersDao")
public interface AllusersDao extends BaseMapper<AllusersEntity> {
	
}
