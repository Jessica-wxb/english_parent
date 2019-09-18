package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.WordPhoneticEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;


/**
 * WordPhoneticService接口
 * wordPhonetic表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
public interface WordPhoneticService extends BaseServicePlus<WordPhoneticEntity> {
    /**
     * 将单词选音标信息导入数据库
     * @param phoneWordPath
     * @author 陈广晗
     * @since 2019-09-06 20:28:46
     * @return
     */
    boolean insertWordPhoneticTable(String phoneWordPath);
}
