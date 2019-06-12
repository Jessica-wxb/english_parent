package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneficEntity;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 根据Id获取音标audio
     * @author 张凯超
     * @param id 音标表 主键Id
     * @return 音标对应音频
     * @datetime 2019年6月12日09:31:37
     */
    PhoneficEntity queryAudioById(@Param("id") Integer id);
}
