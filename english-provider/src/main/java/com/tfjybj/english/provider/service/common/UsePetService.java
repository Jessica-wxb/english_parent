package com.tfjybj.english.provider.service.common;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.model.UsePetModel;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("usePetService")
public class UsePetService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserInfoDao userInfoDao;

    /**
     * @return 查询用户当前正在使用的宠物
     * @Date 2019年9月20日14:05:56
     */
    public String queryUsePetByUserId(){
        // 从taken中获取userId
        String userId = "1071008929686876162";
//        String userId = UserUtil.getCurrentUser().getUserId();
        // 从redis中查询是否有UsePet
        boolean flag = redisUtil.hasKey(EnglishRedis.UsePet + userId);
        if (!flag) {
            UsePetModel usePetModels = userInfoDao.getUsePet(userId);
//            Map<String, Object> map = usePetModels.stream().collect(Collectors.toMap(UsePetModel::getUsePet, UsePetModel -> JSON.toJSONString(UsePetModel)));
            redisUtil.set(EnglishRedis.UsePet+userId,usePetModels.getUsePet() );
        }
        // 从redis中获取
        String userPetJson = redisUtil.get(EnglishRedis.UsePet + userId);
        // 转实体
//        UsePetModel usePetModelNew = JSON.parseObject(userPetJson,UsePetModel.class);


        return userPetJson;
    }

}
