package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.provider.dao.UserSetDao;
import com.tfjybj.english.provider.service.UserSetService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Set;

/**
 * UserSetService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("userSetService")
public class UserSetServiceImpl extends BaseServicePlusImpl<UserSetDao,UserSetEntity> implements UserSetService {

	//region 模板生成
    @Resource
    private UserSetDao userSetDao;

    @Autowired
    private RedisUtil redisUtil;




    /**
     * 通过userid查询用户的设置信息
     * @author 闫伟强
     * @return
     */
    @Override
    public UserSetEntity findUserSetById() {
        System.out.println(UserUtil.getCurrentUser().getUserId());
        boolean flag = redisUtil.hasKey(EnglishRedis.Set+UserUtil.getCurrentUser().getUserId());

        if (!flag) {
            UserSetEntity userSet = userSetDao.findUserSetById(UserUtil.getCurrentUser().getUserId());
            String json = JSON.toJSONString(userSet);
            redisUtil.set(EnglishRedis.Set+UserUtil.getCurrentUser().getUserId(),json);
            return userSet;

        }
        String usersetjson=redisUtil.get(EnglishRedis.Set+UserUtil.getCurrentUser().getUserId());

        UserSetEntity userSetEntity = JSON.parseObject(usersetjson,UserSetEntity.class);

        return userSetEntity;
    }


    /**
     * 通过userid修改用户的设置信息
     * @author 闫伟强
     * @return
     */
    @Override
    public UserSetEntity modifyById(Integer palyNums, Integer isTurnAuto, Integer tuenDelayTime, Integer studyNumber, Integer isShowWord) {
        userSetDao.modifyById(UserUtil.getCurrentUser().getUserId(),palyNums, isTurnAuto, tuenDelayTime, studyNumber,isShowWord);
        UserSetEntity userSetEntity = new UserSetEntity();
        userSetEntity.setPlayNums(palyNums);
        userSetEntity.setIsTurnAuto(isTurnAuto);
        userSetEntity.setTurnDelayTime(tuenDelayTime);
        userSetEntity.setStudyNumber(studyNumber);
        userSetEntity.setIsShowWord(isShowWord);
        String json = JSON.toJSONString(userSetEntity);
        redisUtil.set(EnglishRedis.Set+UserUtil.getCurrentUser().getUserId(),json);
        return userSetEntity;
    }
}
