package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.entity.UserInfoEntity;
import com.tfjybj.english.model.UserPetListModel;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.service.UserInfoService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.service.common.UsePetService;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.JSONUtils;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * UserInfoService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServicePlusImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {

    //region 模板生成
    @Resource
    private UserInfoDao userInfoDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UsePetService usePetService;
    @Resource
    private UserUtil userUtil;


    @Override
    public UserPetListModel qureyPetListByUserId(String userId) {
        return userInfoDao.qureyPetListByUserId(userId);
    }

    @Override
    public boolean buyPet(String userId, String PetList, String usePet) {
        System.out.println(UserUtil.getCurrentUser().getUserId());
//        String userId = "1071008933394640898";
        redisUtil.set(EnglishRedis.UsePet + userId, JSON.toJSONString(usePet));
//        redisUtil.sSet(EnglishRedis.UsePet+ userId, JSON.toJSONString(usePet));
        return userInfoDao.buyPet(userId, PetList, usePet);
    }

    @Override
    public boolean changeUsePet(String userId, String usePet) {
        // 更新redis中ENGLISH:USEPET:userId的value值
        redisUtil.set(EnglishRedis.UsePet + userId, JSON.toJSONString(usePet));
        // 查询当前宠物usePet
        String userPetJson = usePetService.queryUsePetByUserId();
        // 查询出用户的宠物列表pet_list
        UserPetListModel userPetListModel = userInfoService.qureyPetListByUserId(userId);

        /**
         *  1、 截取；号前的数据
         *  2、用用户更换的宠与用于所拥有的petList去对比，筛选出不等于usePet的值放到一个字段。
         *  3、最后将用户更换的宠物放回到petList中的第0个位置（放置到首个）
         */
        List<String> list = Arrays.asList(userPetListModel.getPetList().split(";"));
        List<String> petlist2=list.stream()
                .filter(i->!i.equals(userPetJson))
                .collect(Collectors.toList());
        String [] strings = petlist2.toArray(new String[petlist2.size()]);
        System.out.println(strings);

        // 往petList的第一个位置插入刚购买的宠物
//        String PetList = userPetListModel.getPetList();
//        StringBuilder sb = new StringBuilder(String.valueOf(strings));
//        sb.insert(0,userPetJson+";"); // 在执行的位置0，插入指定的字符串
//        JSONUtils jsonUtils=new JSONUtils();
//        String PetList = JSONUtils.toJSONString(petlist2);
//        System.out.println(PetList);


//        StringBuilder sb = new StringBuilder(PetList);
//        sb.insert(0,usePet+";"); // 在执行的位置0，插入指定的字符串
//        PetList = sb.toString();


        // 将查询出的pet_list和用户刚刚更换的usePet进行对比，如果相等就删掉，存到一个变量usePetSplit里面

        // 将刚更换的usePet插入到usePetSplit里面的第0个位置，存到petListNew里面


        return userInfoDao.changeUsePet(userId, usePet);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


}
