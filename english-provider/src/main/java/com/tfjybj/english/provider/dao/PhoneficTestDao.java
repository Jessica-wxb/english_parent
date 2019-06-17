package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.entity.PhoneficWordEntity;
import com.tfjybj.english.model.PhoneficWordModel;
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
public interface PhoneficTestDao extends BaseMapper<PhoneficWordEntity> {


    List<PhoneficWordModel> queryAudioByPhoneficId(String phoneficId);

    List<PhoneficWordModel> selectWordByPhoneficId(String phoneficId);

    PhoneficWordEntity queryStateByWord(String word);

    //音标练习(听)_根据音标id查询对应正确单词_邢美玲
    List<PhoneficWordModel> getPhoneficTestById(Integer phoneficid);


    /**
     * 根据用户Id查询音标Id、音频
     *
     * @param userId 用户Id
     * @return 音标Id、音频
     * @since 2019年6月13日22:31:07
     */
    List<PhoneficEntity> queryAudioByUserId(@Param("userId") String userId);


    /**
     *根据音标的ID查询的tn_phonefic_word中所有的字段信息
     * @param phoneficId 音标Id
     * @return tn_phonefic_word中所有的字段
     * @since 2019年6月15日10:27:04
     * @autor 冯佳兴
     */
    List<PhoneficWordModel> selectAllById(@Param("phoneficId") String phoneficId);

    List<PhoneficEntity> queryAudioByUserId(@Param("userId") String userId,@Param("num") Integer num);
}
