package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.model.UserSetModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserSetDao接口
 * userSet表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("userSetDao")
public interface UserSetDao extends BaseMapper<UserSetEntity> {

    // 根据用户id，查询用户设置——白爱民2019年6月11日18:13:01
    List<UserSetModel> getStudyNumberService(Integer userid);
}
