package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.WordPhoneticEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WordPhoneticDao接口
 * wordPhonetic表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Repository("wordPhoneticDao")
public interface WordPhoneticDao extends BaseMapper<WordPhoneticEntity> {
	/**
	 * 单词选音标插入redis
	 *
	 * @author 闫伟强
	 * @since 2019年9月7日10:13:21
	 */
	List<WordPhoneticEntity> WordPhoneticInsertRedis();
    long phonePathInstert(WordPhoneticEntity wordPhoneticEntity);
}
