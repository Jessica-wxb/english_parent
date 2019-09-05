package com.tfjybj.english.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class RankModel implements Serializable {

    public RankModel(String userId,String userName,String eAllNum,int rank){
        this.userId = userId;
        this.userName = userName;
        this.eAllNum = eAllNum;
        this.rank = rank;
        this.isLight = isLight;

    }

    private String userId;
    private String userName;
    private String eAllNum;
    private int rank;
    private int isLight;

    //将String类型，转为Int类型
    public Integer getIntRank(){
        return Integer.parseInt(getEAllNum());
    }
}
