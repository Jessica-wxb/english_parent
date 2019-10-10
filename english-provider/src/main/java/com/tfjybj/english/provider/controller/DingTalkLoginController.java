package com.tfjybj.english.provider.controller;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.dmsdbj.itoo.tool.httpclient.HTTPUtils;
import com.tfjybj.english.Enum.DingTalkEnumUntil;
import com.tfjybj.english.model.AccessTokenModel;
import com.tfjybj.english.model.AllUserModel;
import com.tfjybj.english.model.UserDingModel;
import com.tfjybj.english.utils.http.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Api(tags = {"钉钉免登录接口"})
@RequestMapping(value = "/DingTalkLoginController")
@RestController
public class DingTalkLoginController {
    @Value("${ding_corpid}")
    private String corpid;
    @Value("${ding_corpsecret}")
    private String corpsecret;

    /**
     * 获取钉钉token
     *
     * @param code 钉钉临时授权码
     * @return access_token
     */
    @ApiOperation("获取钉钉token")
    @GetMapping(value = "/loginByCode/{code}")
    public AllUserModel GetDingToken(@ApiParam(value = "免密登录临时授权码", required = true) @PathVariable String code) {
        String param = "corpid=" + corpid + "&corpsecret=" + corpsecret;
        String accessToken = HTTPUtils.sendGet(DingTalkEnumUntil.DING_TALK_ENUM_UNTIL_GETTOKEN.getMessage(), param);
        AccessTokenModel accessTokenModel = JSON.parseObject(accessToken, AccessTokenModel.class);
        System.out.println(accessTokenModel);
        String access_token = accessTokenModel.getAccess_token();
        // 根据token和临时code获取userid
        String dingId = this.GetUserInfo(access_token, code);
        AllUserModel allUserModel = this.DingIdLogin(dingId);
        return allUserModel;
    }


    public String GetUserInfo(String access_token, String code) {
        String param = "access_token=" + access_token + "&code=" + code;
        String userInfo = HTTPUtils.sendGet(DingTalkEnumUntil.DING_TALK_ENUM_UNTIL_GETUSERINFO.getMessage(), param);
        UserDingModel userDingModel = JSON.parseObject(userInfo, UserDingModel.class);
        String dingId = userDingModel.getUserid();

        //返回ding_Id
        return dingId;
    }


    public AllUserModel DingIdLogin(String dingId) {
//        dingId = "0306292557772";
        String UserDing = this.sendGet(DingTalkEnumUntil.DING_TALK_ENUM_UNTIL.getMessage(), dingId);
        AllUserModel allUserModel = JSON.parseObject(UserDing, AllUserModel.class);
        return allUserModel;
    }


    public static String sendGet(String url, String param) {
        return sendGet(url, param, false);
    }

    public static String sendGet(String url, String param, Boolean isJson) {
        String result = "";
        BufferedReader in = null;

        try {
            String urlNameString = url +  param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            if (isJson) {
                connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            }

            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            Iterator var9 = map.keySet().iterator();

            while(var9.hasNext()) {
                String key = (String)var9.next();
                System.out.println(key + "--->" + map.get(key));
            }

            String line;
            for(in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8")); (line = in.readLine()) != null; result = result + line) {
            }
        } catch (Exception var19) {
            result = "发送 POST 请求出现异常！";
            var19.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception var18) {
                var18.printStackTrace();
            }

        }

        return result;
    }

}
