package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.entity.PhoneficTestEntity;
import com.tfjybj.english.model.PhoneficTestModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PhoneficTestDao接口
 * phoneficTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Repository("phoneficTestDao")
public interface PhoneficTestDao extends BaseMapper<PhoneficTestEntity> {

    List<PhoneficTestModel> queryAudioByPhoneficId(String phoneficId);

    List<PhoneficTestModel> selectWordByPhoneficId(String phoneficId);

    PhoneficTestEntity queryStateByWord(String word);

    //音标练习(听)_根据音标id查询对应正确单词_邢美玲
    List<PhoneficTestModel> getPhoneficTestById(Integer phoneficid);


    /**
     * 根据用户Id查询音标Id、音频
     * @param userId 用户Id
     * @return 音标Id、音频
     * @since  2019年6月13日22:31:07
     */
    List<PhoneficEntity> queryAudioByUserId(@Param("userId") String userId);

    /**
     * 根据音标ID在音标测试表获取音标
     * @author 张凯超
     * @param phoneficId 音标Id
     * @return 音标Id对应两个音标
     * @since 2019年6月14日09:37:26
     */
    List<PhoneficTestModel> queryPhoneficByPhoneficId(@Param("phoneficId") String phoneficId);
}
