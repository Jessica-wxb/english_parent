package com.tfjybj.english.Enum;


import lombok.Getter;

@Getter
public enum DingTalkEnumUntil {
    DING_TALK_ENUM_UNTIL ("DingLogin","http://192.168.22.54:8081/auth-web/access/noPwdLogin/"),
    DING_TALK_ENUM_UNTIL_GETUSERINFO("DingUserInfo","https://oapi.dingtalk.com/user/getuserinfo"),
    DING_TALK_ENUM_UNTIL_GETTOKEN("DingGetToken","https://oapi.dingtalk.com/gettoken")
    ;
    private  String DingName;
    private  String message;

    DingTalkEnumUntil(String DingName , String message){
        this.DingName = DingName ;
        this.message = message;
    }
}



