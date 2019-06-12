package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneficEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;


/**
 * PhoneficService接口
 * phonefic表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface PhoneficService extends BaseServicePlus<PhoneficEntity> {

    /**
     * 根据Id获取音标audio
     * @author 张凯超
     * @param id 音标表 主键Id
     * @return 音标对应音频
     * @datetime 2019年6月12日09:31:37
     */
    PhoneficEntity queryAudioById(Integer id);
}
