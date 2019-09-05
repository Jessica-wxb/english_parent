package com.tfjybj.english.provider.service.common;

import com.dmsdbj.itoo.sso.utils.UserInfo;
import com.tfjybj.english.model.UserPartModel;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.dao.UserSetDao;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.FastJsonWrapper;
import com.tfjybj.english.utils.http.HttpUtils;
import com.tfjybj.english.utils.http.ResponseWrap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.tfjybj.english.utils.cache.RedisUtil;

import javax.annotation.Resource;

/**
 * UserInfoService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("userInfoAndSetService")
public class UserInfoAndSetService {


    @Value("${auth-login}")
    private String auth;

    //region 模板生成
    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserSetDao userSetDao;

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 用户登录
     *
     * @return
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    public UserPartModel login(String userCode) {
        UserPartModel userPartModel = userInfoDao.login(userCode);
        if(userPartModel == null){
            return null;
        }
        String str = FastJsonWrapper.toJson(userPartModel);
        redisUtil.set(EnglishRedis.UserInfo+userCode, str);
        return userPartModel;
    }

    /**
     * 登陆鉴权
     *
     * @return token
     * @author 闫伟强
     * @since 2019年8月21日17:14:10
     */
    public String authentication(String body) {
        HttpUtils http = HttpUtils.post(auth);
        http.addHeader("Content-Type", "application/json; charset=utf-8");
        http.setParameter(body);
        ResponseWrap responseWrap = http.execute();
        return responseWrap.getString();
    }
}
