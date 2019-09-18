package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneticRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PhoneticRecordDao接口
 * phoneticRecord表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Repository("phoneticRecordDao")
public interface PhoneticRecordDao extends BaseMapper<PhoneticRecordEntity> {

    /**
     * 将已经学习完的音标同步到DB
     * @author 闫伟强
     */
    void insertList(List<PhoneticRecordEntity> list);

    /**
     * @param
     * @return 进入音标检测
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    List<String> findPhoneticCheck(String userId);

    /**
     * @param
     * @return 批量修改音标记录里的isckeck
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    void modfiyIsCheck(List<PhoneticRecordEntity> list);

    /**
     * @param
     * @return 批量修改音标记录里的isckeck
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    Integer checkPhoneticNums(@Param("userId") String userId);
}
