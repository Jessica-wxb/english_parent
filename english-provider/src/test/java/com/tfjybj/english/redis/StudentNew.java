package com.tfjybj.english.redis;

import lombok.Data;

@Data
public class StudentNew {

    public StudentNew(String user_id,String user_name,String age){
        this.age = age;
        this.user_id = user_id;
        this.user_name = user_name;

    }

    private String user_id;
    private String user_name;
    private String age;
}
