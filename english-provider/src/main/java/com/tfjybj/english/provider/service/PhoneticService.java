package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.PhoneticEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;

import java.io.IOException;


/**
 * PhoneticService接口
 * phonetic表
 *
 * @author 白靖
 * @version ${version}
 * @since ${version} 2019-09-05 17:36:14
 */
public interface PhoneticService extends BaseServicePlus<PhoneticEntity> {

    /**
     * 查询要学习的音标
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     */
    PhoneticEntity PhoneticToDo();
    /**
     * 左滑下一个音标
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     * @param phonetic
     */
    PhoneticEntity NextPhonetic(String phonetic);
    /**
     * 侧边点击音标进行查询
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     */
    PhoneticEntity queryPhonetic(String phonetic);

    /**
     * 根据目录结构插入音标
     *
     * @param phonePath 文件所在的路径
     * @return true/false
     * @author 陈广晗
     * @since 2019-09-06 17:21:46
     */
    boolean phonePathInstert(String phonePath) throws IOException;

}
