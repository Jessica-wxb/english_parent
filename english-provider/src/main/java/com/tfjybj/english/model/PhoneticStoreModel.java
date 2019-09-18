package com.tfjybj.english.model;

import lombok.Data;

@Data
public class PhoneticStoreModel {
    private String phonetic;
    private String audio;
    private String correctAudio;
    private String correctPic;
    private String falseAudio;
    private String falsePic;
    private Integer falseType;
}
