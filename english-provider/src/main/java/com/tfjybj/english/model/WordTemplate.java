package com.tfjybj.english.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class WordTemplate implements Serializable {

    private String wordId;
    private String rightPicture;
    private String otherPicture1;
    private String otherPicture2;
    private String otherPicture3;
}
