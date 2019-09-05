package com.tfjybj.english.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RankLocalModel {

    private String userId;
    private String userName;
    private String eAllNum;
    private int rank;
    private int isLight;
}
