package com.tfjybj.english.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.tfjybj.english.TestBaseProject;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.model.UserPartModel;
import com.tfjybj.english.model.UserSetModel;
import com.tfjybj.english.model.WordDetection;
import com.tfjybj.english.provider.service.UserSetService;
import com.tfjybj.english.provider.service.common.UserInfoAndSetService;
import com.tfjybj.english.provider.service.common.WordDetectionService;
import com.tfjybj.english.provider.service.impl.PhoneticServiceImpl;
import com.tfjybj.english.provider.service.impl.PhoneticWordServiceImpl;
import com.tfjybj.english.provider.service.impl.WordPhoneticServiceImpl;
import com.tfjybj.english.utils.cache.RedisUtil;
import com.tfjybj.english.utils.http.HttpUtils;
import com.tfjybj.english.utils.http.ResponseWrap;
import org.junit.Test;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.faces.context.RequestCookieMap;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

public class RecordEngLishTest extends TestBaseProject {


    @Value("${auth-login}")
    private String auth;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserSetService userSetService;

    @Autowired
    UserInfoAndSetService userInfoAndSetService;

    @Autowired
    WordDetectionService wordDetectionService;


    @Autowired
    PhoneticWordServiceImpl phoneticWordService;

    @Autowired
    WordPhoneticServiceImpl wordPhoneticService;

    @Autowired
    PhoneticServiceImpl phoneticService;


    @Test
    public void test(){
        redisUtil.set("qmx1","qmx2");
        String str = redisUtil.get("qmx1");
        System.out.println(str);
    }

    @Test
    public void htest(){
        redisUtil.hset("EngLish:userId:Date","hqmx1","123");
        redisUtil.hset("hqmx1","hqmx2","234");
        redisUtil.hset("hqmx1","hqmx3","345");
        String str = redisUtil.hget("hqmx1","hqmx2");
        System.out.println(str);
    }

    @Test
    public void httpclientTest() throws UnsupportedEncodingException {
        HttpUtils http = HttpUtils.post("http://dmsdbj.com/auth-web/access/login");
        http.addHeader("Content-Type", "application/json; charset=utf-8");
        String body = "{\n" +
                "\n" +
                "  \"password\": \"15732677791\",\n" +
                "\n" +
                "  \"userCode\": \"15732677791\"\n" +
                "\n" +
                "}";
//        StringEntity userEntity = new StringEntity(body);
        http.setParameter(body);
        ResponseWrap responseWrap = http.execute();
        System.out.println(responseWrap.getString());
    }

    @Test
    public void testfindUserSetById(){
        UserSetEntity userSetById = userSetService.findUserSetById();
        System.out.println(userSetById.toString());
    }

    @Test
    public void modifyById(){
//        UserSetModel userSetEntity= new UserSetModel();
//        userSetEntity.setUserId("1");
//        userSetEntity.setPlayNums(2);
//        userSetEntity.setIsTurnAuto(2);
//        userSetEntity.setTurnDelayTime(2);
//        userSetEntity.setStudyNumber(2);

        UserSetEntity userSetEntity1 = userSetService.modifyById(2,2,2,2);
        System.out.println(userSetEntity1.toString());
    }

    @Test
    public void testUserInfo(){
        String userCode = "1";
        UserPartModel userPartModel = userInfoAndSetService.login(userCode);
        String userCode2 = "5";
        UserPartModel userPartModel2 = userInfoAndSetService.login(userCode2);

        String userCode1 = "2";
        UserPartModel userPartModel3 = userInfoAndSetService.login(userCode1);
        if(userPartModel3 == null){
            System.out.println("Ok");
        }

    }

    @Test
    public void daoru(){
        wordDetectionService.toWordTemplate();
    }


    @Test
    public void PhoneticWordInsertRedis(){
        phoneticWordService.PhoneticWordInsertRedis();
    }
    @Test
    public void WordPhoneticInsertRedis(){
        wordPhoneticService.WordPhoneticInsertRedis();
    }
    @Test
    public void PhoneticInsertRedis(){
        phoneticService.PhoneticInsertRedis();
    }
    //    @Test
//    public void daoru(){
//        wordDetectionService.toWordTemplate();
//    }

}
