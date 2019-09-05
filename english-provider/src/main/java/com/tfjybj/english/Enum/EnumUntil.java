package com.tfjybj.english.Enum;


import lombok.Getter;

@Getter
public enum  EnumUntil {
    SELECT_SUCCESS(0,"查询成功"),
    SELECT_FAIL(1,"查询失败"),
    SELECT_FINISH(2,"无匹配数据"),
    MODIFY_SUCCESS(3,"修改成功"),
    MODIFY_DAIL(4,"修改失败"),
    MODIFY_FINISH(5,"更新完成")
    ;
    private  Integer code;
    private  String message;

    EnumUntil(Integer code ,String message){
        this.code = code ;
        this.message = message;
    }
}



