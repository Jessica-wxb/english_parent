package com.tfjybj.english.model;

import lombok.Data;

@Data
public class WordWrongNewModel {
    // 单词学习、检测使用
    private String  wordId;
    private String userId;
    private Integer isCheck;
    private Integer isStudy;
}
