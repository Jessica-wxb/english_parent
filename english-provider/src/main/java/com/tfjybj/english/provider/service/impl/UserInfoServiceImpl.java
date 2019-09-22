package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.tfjybj.english.entity.UserInfoEntity;
import com.tfjybj.english.model.UserPetListModel;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.service.UserInfoService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * UserInfoService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServicePlusImpl<UserInfoDao,UserInfoEntity> implements UserInfoService {
	
	//region 模板生成
    @Resource
    private UserInfoDao userInfoDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserInfoService userInfoService;


    @Override
    public UserPetListModel qureyPetListByUserId(String userId) {
            return userInfoDao.qureyPetListByUserId(userId);
    }

    @Override
    public boolean buyPet(String userId, String PetList, String usePet) {
//        System.out.println(UserUtil.getCurrentUser().getUserId());
//        String userId = "1071008933394640898";
        redisUtil.set(EnglishRedis.UsePet+userId, JSON.toJSONString(usePet));
//        redisUtil.sSet(EnglishRedis.UsePet+ userId, JSON.toJSONString(usePet));
        return userInfoDao.buyPet(userId,PetList,usePet);
    }

    @Override
    public boolean changeUsePet(String userId, String usePet) {
        // 更新redis中ENGLISH:USEPET:userId的value值
        redisUtil.set(EnglishRedis.UsePet+ userId, JSON.toJSONString(usePet));

        // 查询出用户的宠物列表pet_list
//        UserPetListModel userPetListModel = userInfoService.qureyPetListByUserId(userId);
//        // 截取；号前的数据
//        String [] PetList = userPetListModel.getPetName().split(";");
//        String usePetUrl = null;
//        // 将查询到的用户当前正在使用的宠物与枚举对比获取当前宠物地址
//        if(PetList.equals(usePet)){
////            usePetUrl = PetListEnumUntil.PET_DOG.getPetUrl();
//            String usePetSplit = remove.(PetList.equals(usePet));
//        }
        // 将查询出的pet_list和用户刚刚更换的usePet进行对比，如果相等就删掉，存到一个变量usePetSplit里面

        // 将刚更换的usePet插入到usePetSplit里面的第0个位置，存到petListNew里面


        return userInfoDao.changeUsePet(userId,usePet);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


}
