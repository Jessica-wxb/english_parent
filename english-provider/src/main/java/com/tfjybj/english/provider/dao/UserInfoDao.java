package com.tfjybj.english.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tfjybj.english.entity.UserInfoEntity;
import com.tfjybj.english.model.*;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserInfoDao接口
 * userInfo表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Repository("userInfoDao")
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {

    /**
     * 用户登录
     * @return
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    UserPartModel login(String userCode);

    //排行榜（查询所有userName、EAllNum、rank）--董可
    List<RankModel> queryUserIdEAllNum();

    //查询“我的”--董可
    List<MineModel> queryMineByUserId(@Param("userId")String userId);


    //循环更新用户的E币
    void modflyEB(List<RankModel> rankModel);
    //根据登录的userId查询此用户的user_name，e_all_num
    RankModel queryUserIdAddE(String userId);

    // 修改坚持天数--董可--2019年8月29日
    void updateInsistDay(String userId);

    // 获取updatetime
    String selectNowDay(String userId);

    // 获取createtime
        String selectCreateDay(String userId);



    //获取坚持天数
    String selectInsistDays(String userId);

    //获取用户当前宠物列表
    UserPetListModel queryPetListByUserId(@Param("userId") String userId);

    /**
     * 根据userId在tn_user_info表中查询当前宠物
     * @param  userId
     * @return usePet
     */
    UsePetModel getUsePet(String userId);


    boolean buyPet(@Param("userId") String userId, @Param("PetList") String PetList,@Param("usePet") String usePet);


    boolean changeUsePet(@Param("userId") String userId, @Param("usePet") String usePet);
}
