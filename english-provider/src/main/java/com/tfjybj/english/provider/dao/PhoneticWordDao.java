package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneticWordEntity;
import com.tfjybj.english.entity.WordPhoneticEntity;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PhoneticWordDao接口
 * phoneticWord表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Repository("phoneticWordDao")
public interface PhoneticWordDao extends BaseMapper<PhoneticWordEntity> {
    long phonePathInstert(PhoneticWordEntity phoneticWordEntity);
    /**
     * 音标选单词插入redis
     *
     * @author 闫伟强
     * @since 2019年9月7日10:13:21
     */
    List<PhoneticWordEntity> PhoneticWordInsertRedis();
}
