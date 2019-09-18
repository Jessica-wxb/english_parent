package com.tfjybj.english.model;

import lombok.Data;

@Data
public class PhoneticCheckModel {
    private String phonetic;
    private String wordAudio;
    private String phoneticCorrectAudio;
    private String phoneticCorrectPic;
    private String phoneticFalseAudio;
    private String phoneticFalsePic;
    private String phoneticAudio;
    private String wordCorrectAudio;
    private String wordCorrectPic;
    private String wordFalseAudio;
    private String wordFalsePic;
    private Integer isCheck;
}
