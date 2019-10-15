package com.tfjybj.english.model;

import lombok.Data;

import java.util.List;

@Data
public class AllUserModel {
    private String  code;
    private String  message;
    private List<AccessTokenModel> data;
}
