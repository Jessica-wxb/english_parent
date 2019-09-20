package com.tfjybj.english.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class MineModel {

    public MineModel(String userId,String userName,String eAllNum,int rank){
        this.userId = userId;
        this.userName = userName;
        this.insistDays = insistDays;
        this.wordNum = wordNum;
        this.eNowNum = eNowNum;
    }

    private String userId;
    private String userName;
    private String insistDays;
    private String wordNum;
    private String eNowNum;

    public String getENowNum(String valueOf){
        return eNowNum;

    }
}
