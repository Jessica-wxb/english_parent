package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneficEntity;
import org.springframework.stereotype.Repository;

/**
 * PhoneficDao接口
 * phonefic表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("phoneficDao")
public interface PhoneficDao extends BaseMapper<PhoneficEntity> {
	
}
