package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.entity.UserInfoEntity;
import com.tfjybj.english.model.UserInfoModel;
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
    public UserInfoModel buyPet(String userId, String PetList,String usePet) {
//        System.out.println(UserUtil.getCurrentUser().getUserId());
//        String userId = "1071008933394640898";
        redisUtil.set(EnglishRedis.UsePet+userId, JSON.toJSONString(usePet));
//        redisUtil.sSet(EnglishRedis.UsePet+ userId, JSON.toJSONString(usePet));
        return userInfoDao.buyPet(userId,PetList,usePet);
    }

    @Override
    public UserInfoModel changeUsePet(String userId,String usePet) {
        // 查询出用户的宠物列表pet_list
        UserPetListModel userPetListModel = userInfoService.qureyPetListByUserId(userId);
        redisUtil.sSet(EnglishRedis.UsePet+UserUtil.getCurrentUser().getUserId(), JSON.toJSONString(usePet));
        return userInfoDao.changeUsePet(userId,usePet);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


}
