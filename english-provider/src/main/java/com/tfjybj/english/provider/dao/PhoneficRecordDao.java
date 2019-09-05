package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneficRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * PhoneficRecordDao接口
 * phoneficRecord表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Repository("phoneficRecordDao")
public interface PhoneficRecordDao extends BaseMapper<PhoneficRecordEntity> {
	
}
