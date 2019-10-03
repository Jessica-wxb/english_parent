package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneticEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PhoneticDao接口
 * phonetic表
 *
 * @author 白靖
 * @version ${version}
 * @since ${version} 2019-09-05 17:36:14
 */
@Repository("phoneticDao")
public interface PhoneticDao extends BaseMapper<PhoneticEntity> {
    long phonePathInstert(PhoneticEntity phoneticEntity);

    List<PhoneticEntity> PhoneticInsertRedis();


}
