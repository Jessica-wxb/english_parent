package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.UserInfoEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.RankModel;
import com.tfjybj.english.model.UserInfoModel;
import com.tfjybj.english.model.UserPetListModel;

import java.util.List;


/**
 * UserInfoService接口
 * userInfo表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
public interface UserInfoService extends BaseServicePlus<UserInfoEntity> {

    /**
     * @param  userId
     * @return 获取用户的宠物列表 petList
     *
     */

    UserPetListModel qureyPetListByUserId(String userId);


    UserInfoModel buyPet(String userId, String PetList,String usePet);

    UserInfoModel changeUsePet(String userId,String usePet);
}
