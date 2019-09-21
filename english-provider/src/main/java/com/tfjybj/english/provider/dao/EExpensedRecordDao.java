package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.EExpensedRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * EExpensedRecordDao接口
 * eExpensedRecord表
 *
 * @author 王小波
 * @version ${version}
 * @since ${version} 2019-09-20 16:05:03
 */
@Repository("eExpensedRecordDao")
public interface EExpensedRecordDao extends BaseMapper<EExpensedRecordEntity> {


    int InsertExpensedRecord(@Param("id") String id,@Param("userId") String userId, @Param("description") String description, @Param("expensedENum") String expensedENum );

}
