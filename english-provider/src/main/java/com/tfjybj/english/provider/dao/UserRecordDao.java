package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.tfjybj.english.entity.UserRecordEntity;
        import com.tfjybj.english.model.UserRecordModel;
        import org.apache.ibatis.annotations.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;

/**
 * UserRecordDao接口
 * userRecord表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("userRecordDao")
public interface UserRecordDao extends BaseMapper<UserRecordEntity> {
    //查询该用户id的已学单词_邢美玲
    int findStudyWordById(int userid);

    // 根据用户id查询未检测的单词-张伟杰
    List<UserRecordModel> queryNoDetectedByUId(String userId);

    /**
     * 根据用户Id和当天时间,查询音标的ID
     * @param userId 用户ID
     * @return 当天学习过的音标ID(phonefic_id)
     * @since 2019年6月15日11:55:03
     * @autor 冯佳兴
     */
    List<UserRecordModel> selectPhonefic_idByUserIdAndcreatetime(@Param("userId") String userId);
}
