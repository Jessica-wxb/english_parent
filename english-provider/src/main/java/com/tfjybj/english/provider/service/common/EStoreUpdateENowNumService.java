package com.tfjybj.english.provider.service.common;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.model.MineModel;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tfjybj.english.model.MineModel;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("eStoreUpdateENowNumService")
public class EStoreUpdateENowNumService{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserInfoDao userInfoDao;


    /**
     *@ClassName EStoreUpdateENowNumService
     *@param  userCode, expensedENum$
     *@Description  用户在E币商城消费后，更新用户当前可用的ENowNum
     *@Author
     *@Date 2019/9/20 11:18
     *@Version 1.0
     **/

    public  String UpdateENum(String userCode,String expensedENum){
        // 获取userId
        String userId = UserUtil.getCurrentUser().getUserId();
//        String userId = "1071008933394640898";
//        String userCode ="1233213";
        // 在redis中查询是否有userInfo
        boolean flag = redisUtil.hasKey(EnglishRedis.UserInfo + userCode);
        // 判断redis里面是否存在有e_now_num,如果redis中有数据则查询成功，
        // 如果redis里面不存在e_now_num，就到数据库中的tn_user_info表中去查，
        // 如果数据库中有数据，就将tn_user_info中的e_now_num存到redis中，
        // 然后从redis中查询出e_now_num，并更新redis的e_now_num
        if(!flag){
            List<MineModel> mineModels = userInfoDao.queryMineByUserId(userId);
            Map<String,Object> map = mineModels.stream().collect(Collectors.toMap(MineModel::getUserId,MineModel -> JSON.toJSONString(MineModel)));
            redisUtil.hmset(EnglishRedis.UserInfo,map);
        }
        // 从redis中获取
        String MineModelJson = redisUtil.get(EnglishRedis.UserInfo + userCode);
        // 转实体
        MineModel mineModel = JSON.parseObject(MineModelJson,MineModel.class);
        // 获取e_now_num
        // 在MineModel中添加了个getENowNum的方法
        String eNowNum = mineModel.getENowNum(String.valueOf(mineModel));
        // 将用户当前的eNowNum减去被消费的E币数expensedENum，将得出最新可用的eNowNum值set到mineModel里面
        mineModel.setENowNum(String.valueOf(Integer.valueOf(mineModel.getENowNum())-Integer.valueOf(expensedENum)) );
       // 将得出的ENowNum保存到redis里面
        redisUtil.set(EnglishRedis.UserInfo+userCode,JSON.toJSONString(mineModel));
        return mineModel.getENowNum();

    }



}
