package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.entity.UserInfoEntity;
import com.tfjybj.english.model.MineModel;
import com.tfjybj.english.model.UsePetModel;
import com.tfjybj.english.model.UserPetListModel;
import com.tfjybj.english.provider.dao.EExpensedRecordDao;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.service.UserInfoService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.service.common.EStoreUpdateENowNumService;
import com.tfjybj.english.provider.service.common.UsePetService;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private EExpensedRecordDao eExpensedRecordDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UsePetService usePetService;
    //    @Resource
    @Autowired
    private EStoreUpdateENowNumService eStoreUpdateENowNumService;
//    @Resource
//    private UserUtil userUtil;


    @Override
    public UserPetListModel queryPetListByUserId(String userId) {
        return userInfoDao.queryPetListByUserId(userId);
    }


    /**
     * -------------------------购买宠物-------------------------
     *
     * @param userCode
     * @param usePet
     * @param description
     * @param expensedENum
     * @return 业务逻辑：
     * 购买宠物->成功之后：
     * 0.往E币消费记录表tn_e_expensed_record中插入一条消费记录
     * 1.更新redis中ENGLISH:USEPET:userId中的当前宠物替换成刚刚购买的宠物
     * 2.更新数据库中的usePet和petList
     * 3.用户在E币商城消费后，更新用户当前可用的ENowNum
     * 购买宠物->失败之后：提示插入失败
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public boolean buyPet(String userCode, String usePet, String description, String expensedENum) {

        String userId = UserUtil.getCurrentUser().getUserId();
        // 获取当前时间
        Date date = new Date();

        if (redisUtil.set(EnglishRedis.UsePet + userId, usePet)) {
            eExpensedRecordDao.InsertExpensedRecord(IdWorker.getIdStr(), userId, description, expensedENum, date);
            // 更新redis中ENGLISH:USEPET:userId中的当前宠物替换成刚刚购买的宠物
            UserPetListModel userPetListModel = userInfoService.queryPetListByUserId(userId);
            // 用户在E币商城消费后，更新用户当前可用的ENowNum
            UpdateENum(userCode, expensedENum);
            // 从redis中查询当前用户正在使用的宠物，如果没有从tn_user_info表中查询当前宠物，然后同步到redis中
            queryUsePetByUserId();
            // 往petList的第一个位置插入刚购买的宠物
            String PetList = userPetListModel.getPetList();
            StringBuilder sb = new StringBuilder(PetList);
            sb.insert(0, usePet + ";"); // 在执行的位置0，插入指定的字符串
            PetList = sb.toString();
            userInfoDao.buyPet(userId, PetList, usePet);

        } else {
            return false;
        }


        return true;
    }


    /**
     * @param userCode, expensedENum$
     * @ClassName EStoreUpdateENowNumService
     * @Description 用户在E币商城消费后，更新用户当前可用的ENowNum
     * @Author
     * @Date 2019/9/20 11:18
     * @Version 1.0
     **/

    @Override
    public String UpdateENum(String userCode, String expensedENum) {
        /*
          0.先从redis中查询e_now_num
            判断redis里面是否存在e_now_num
            1.是->查询成功
            2.不是->从数据库查
                2.1 数据库存在,从库中查，并将得出的ENowNum同步到redis
            3. 从redis中取出数据
               3.1.并将取出的数据转化成实体
               3.2从实体中取出ENowNum
               3.3 将ENowNum的值减去消费的E币数=新的ENowNum,并存到redis中
         */
        // 获取userId
        String userId = UserUtil.getCurrentUser().getUserId();

        boolean flag = redisUtil.hasKey(EnglishRedis.UserInfo + userCode);
        if (!flag) {
            List<MineModel> mineModels = userInfoDao.queryMineByUserId(userId);
            Map<String, Object> map = mineModels.stream().collect(Collectors.toMap(MineModel::getUserId, MineModel -> JSON.toJSONString(MineModel)));
            redisUtil.hmset(EnglishRedis.UserInfo, map);
        }
        String MineModelJson = redisUtil.get(EnglishRedis.UserInfo + userCode);
        MineModel mineModel = JSON.parseObject(MineModelJson, MineModel.class);
        String eNowNum = mineModel.getENowNum(String.valueOf(mineModel));
        mineModel.setENowNum(String.valueOf(Integer.valueOf(mineModel.getENowNum()) - Integer.valueOf(expensedENum)));
        redisUtil.set(EnglishRedis.UserInfo + userCode, JSON.toJSONString(mineModel));
        return mineModel.getENowNum();

    }

    /**
     * @return 查询用户当前正在使用的宠物
     * @Date 2019年9月20日14:05:56
     */
    @Override
    public String queryUsePetByUserId() {
        /*
            0.先从redis中查询UsePet
            判断redis里面是否存在UsePet
            1.是->查询成功
            2.不是->从数据库查
                2.1 数据库存在,从库中查，并将得出的ENowNum同步到redis
                    2.1.1 从redis中取出数据 UsePet
        */
        // 从taken中获取userId
        String userId = UserUtil.getCurrentUser().getUserId();
        boolean flag = redisUtil.hasKey(EnglishRedis.UsePet + userId);
        if (!flag) {
            UsePetModel usePetModels = userInfoDao.getUsePet(userId);
            // 判断如果从数据库中查询到的数据为空，则返回，提示查询失败
            if (usePetModels.getUsePet() == null && usePetModels.getUsePet() == "") {
                return ItooResult.FAIL;

            }
            redisUtil.set(EnglishRedis.UsePet + userId, usePetModels.getUsePet());
        }
        String userPetJson = redisUtil.get(EnglishRedis.UsePet + userId);
        return userPetJson;
    }


    /**
     * --------------------------更换宠物形象-------------------------------
     *
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
     * -----------------------------积分兑换---------------------------
     *
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
        eExpensedRecordDao.InsertExpensedRecord(IdWorker.getIdStr(), userId, description, expensedENum, date);
        // 用户在E币商城消费后，更新用户当前可用的ENowNum
        UpdateENum(userCode, expensedENum);
        return true;
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


}
