package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.NewPictureEntity;
import com.tfjybj.english.model.UserNewpictureModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NewPictureDao接口
 * newPicture表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Repository("newPictureDao")
public interface NewPictureDao extends BaseMapper<NewPictureEntity> {

    List<UserNewpictureModel> newPictureInsertRedis();
}
