package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneticWordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;


/**
 * PhoneticWordService接口
 * phoneticWord表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
public interface PhoneticWordService extends BaseServicePlus<PhoneticWordEntity> {
    /**
     * 将单词选音标信息导入数据库
     * @param phoneWordPath
     * @author 陈广晗
     * @since 2019-09-06 20:28:46
     * @return
     */
    boolean insertPhoneticWordTable(String phoneWordPath);
}
