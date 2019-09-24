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
//        String userId = UserUtil.getCurrentUser().getUserId();
        String userId = "1071008924553048065";
        boolean flag = redisUtil.hasKey(EnglishRedis.UserInfo + userCode);
        if(!flag){
            List<MineModel> mineModels = userInfoDao.queryMineByUserId(userId);
            Map<String,Object> map = mineModels.stream().collect(Collectors.toMap(MineModel::getUserId,MineModel -> JSON.toJSONString(MineModel)));
            redisUtil.hmset(EnglishRedis.UserInfo,map);
        }
        String MineModelJson = redisUtil.get(EnglishRedis.UserInfo + userCode);
        MineModel mineModel = JSON.parseObject(MineModelJson,MineModel.class);
        String eNowNum = mineModel.getENowNum(String.valueOf(mineModel));
        mineModel.setENowNum(String.valueOf(Integer.valueOf(mineModel.getENowNum())-Integer.valueOf(expensedENum)) );
        redisUtil.set(EnglishRedis.UserInfo+userCode,JSON.toJSONString(mineModel));
        return mineModel.getENowNum();

    }



}
