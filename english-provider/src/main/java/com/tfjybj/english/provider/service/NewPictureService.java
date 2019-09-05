package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.NewPictureEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.UserNewpictureModel;

import java.util.List;


/**
 * NewPictureService接口
 * newPicture表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
public interface NewPictureService extends BaseServicePlus<NewPictureEntity> {

    List<UserNewpictureModel> newPictureInsertRedis();
}
