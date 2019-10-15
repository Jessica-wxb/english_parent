package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneticWrongEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PhoneticWrongDao接口
 * phoneticWrong表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Repository("phoneticWrongDao")
public interface PhoneticWrongDao extends BaseMapper<PhoneticWrongEntity> {
    long insertBatch(List<PhoneticWrongEntity> phoneticWrongEntities);
}
