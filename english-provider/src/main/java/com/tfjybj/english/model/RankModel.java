package com.tfjybj.english.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class RankModel implements Serializable {

    public RankModel(String userId,String userName,String eAllNum,int rank,int isLight){
        this.userId = userId;
        this.userName = userName;
        this.eAllNum = eAllNum;
        this.rank = rank;
        this.isLight = isLight;

    }

    public String userId;
    public String userName;
    public String eAllNum;
    public int rank;
    public int isLight;

    //将String类型，转为Int类型
    public Integer getIntRank(){
        return Integer.parseInt(getEAllNum()!= null ? getEAllNum() : "0");
    }
/*
    public String toJsonString(){
        return JSON.toJSONString(this);
    }*/
}
