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
            redisUtil.set(EnglishRedis.UsePet+userId,usePetModels.getUsePet() );
        }
        String userPetJson = redisUtil.get(EnglishRedis.UsePet + userId);
        return userPetJson;
    }

}
