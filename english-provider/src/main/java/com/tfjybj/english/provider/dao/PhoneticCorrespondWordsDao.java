package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneticCorrespondWordsEntity;
import org.springframework.stereotype.Repository;

/**
 * PhoneticCorrespondWordsDao接口
 * phoneticCorrespondWords表
 *
 * @author 马莹
 * @version 1.0.0
 * @since 2019-06-14 16:48:08
 */
@Repository("phoneticCorrespondWordsDao")
public interface PhoneticCorrespondWordsDao extends BaseMapper<PhoneticCorrespondWordsEntity> {
	
}
