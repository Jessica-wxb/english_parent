package com.tfjybj.english.model;

import lombok.Data;

@Data
public class HomePageNumsModel {

    private String userId;
    //学单词数
    private Integer wordsStay;
    //单词检测数
    private Integer toCheckNum;
    //单词归仓数
    private Integer storeNums;

    //归仓检测数
    private Integer storeCheckNums;

    private int checkIsUsed;
    private int storeIsUsed;
    private int buttonIsWhat;

    private Integer phoneticCheckNums;
    //音标归仓
    private Boolean phoneticStoreToDo;
}
