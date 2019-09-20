package com.tfjybj.english.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsePetModel {
    public UsePetModel(String userId,String usePet){
        this.userId = userId;
        this.usePet = usePet;
    }
    private String userId;
    private String usePet;
}
