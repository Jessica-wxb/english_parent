package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneticCorrespondWordsEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.PhoneticCorrespondWordsModel;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    /**
     * 根据音标ID查询对应的单词
     * @return 音标所对应的单词
     * @param id 音标id
     * @author 闫伟强
     * @since ${version} 2019年6月15日19:17:28
     */
    List<PhoneticCorrespondWordsModel> findWordById(String id);
}
