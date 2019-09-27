package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.entity.UserInfoEntity;
import com.tfjybj.english.model.UserPetListModel;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.service.UserInfoService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.service.common.EStoreUpdateENowNumService;
import com.tfjybj.english.provider.service.common.InsertExpensedRecordService;
import com.tfjybj.english.provider.service.common.UsePetService;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

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
//    @Resource
//    private UserUtil userUtil;
    @Autowired
    private InsertExpensedRecordService insertExpensedRecordService;
    @Autowired
    private EStoreUpdateENowNumService eStoreUpdateENowNumService;
//    @Resource
//    private UserUtil userUtil;


    @Override
    public UserPetListModel queryPetListByUserId(String userId) {
            return userInfoDao.queryPetListByUserId(userId);
    }


    /**
     *  -------------------------购买宠物-------------------------
     * @param userCode
     * @param usePet
     * @param description
     * @param expensedENum
     * @return
     *
     *
     *    业务逻辑：
     *             购买宠物->成功之后：
     *             0.往E币消费记录表tn_e_expensed_record中插入一条消费记录
     *             1.更新redis中ENGLISH:USEPET:userId中的当前宠物替换成刚刚购买的宠物
     *             2.更新数据库中的usePet和petList
     *             3.用户在E币商城消费后，更新用户当前可用的ENowNum
     *             购买宠物->失败之后：提示插入失败
     *
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public boolean buyPet(String userCode, String usePet,String description,String expensedENum) {

        String userId = UserUtil.getCurrentUser().getUserId();
        // 获取当前时间
        Date date = new Date();

        redisUtil.set(EnglishRedis.UsePet+ userId, usePet);
        insertExpensedRecordService.InsertExpensedRecord(IdWorker.getIdStr(),userId,description,expensedENum,date);
        // 更新redis中ENGLISH:USEPET:userId中的当前宠物替换成刚刚购买的宠物
        UserPetListModel userPetListModel = userInfoService.queryPetListByUserId(userId);
        // 用户在E币商城消费后，更新用户当前可用的ENowNum
        eStoreUpdateENowNumService.UpdateENum(userCode, expensedENum);
        // 从redis中查询当前用户正在使用的宠物，如果没有从tn_user_info表中查询当前宠物，然后同步到redis中

        usePetService.queryUsePetByUserId();
        // 往petList的第一个位置插入刚购买的宠物
        String PetList = userPetListModel.getPetList();
        StringBuilder sb = new StringBuilder(PetList);
        sb.insert(0,usePet+";"); // 在执行的位置0，插入指定的字符串
        PetList = sb.toString();

        return userInfoDao.buyPet(userId, PetList, usePet);
    }


    /**
     *
     * --------------------------更换宠物形象-------------------------------
     * @param userId
     * @param usePet
     * @return
     */

    @Override
    public boolean changeUsePet(String userId, String usePet) {
        // 更新redis中ENGLISH:USEPET:userId的value值
        redisUtil.set(EnglishRedis.UsePet + userId, usePet);
        // 查询当前宠物usePet
        String userPetJson = usePetService.queryUsePetByUserId();
        // 查询出用户的宠物列表pet_list
        UserPetListModel userPetListModel = userInfoService.queryPetListByUserId(userId);
        return userInfoDao.changeUsePet(userId, usePet);
    }

    /**
     *
     *  -----------------------------积分兑换---------------------------
     * @param userCode
     * @param description  类型描述:积分兑换 + 7
     * @param expensedENum 消费的E币数
     * @return
     */
    @Override
    public boolean changeIntegral(String userCode, String description, String expensedENum) {
        String userId = UserUtil.getCurrentUser().getUserId();
        // 获取当前时间
        Date date = new Date();
        insertExpensedRecordService.InsertExpensedRecord(IdWorker.getIdStr(),userId,description,expensedENum,date);
        // 用户在E币商城消费后，更新用户当前可用的ENowNum
        eStoreUpdateENowNumService.UpdateENum(userCode, expensedENum);
        return false;
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


}
