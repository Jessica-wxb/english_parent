package com.tfjybj.english.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * Created by Administrator on 2019/8/17.
 */
@Data
public class WordPartModel {
    /**
     * 单词
     */
    @ApiModelProperty(value = "id")
    @Column(name = "id")
    private String id;

    /**
     * 单词
     */
    @ApiModelProperty(value = "单词")
    @Column(name = "word")
    private String word;

    /**
     * 单词图片地址1
     */
    @ApiModelProperty(value = "单词图片地址1")
    @Column(name = "word_picture1")
    private String wordPicture1;

    /**
     * 单词图片地址2
     */
    @ApiModelProperty(value = "单词图片地址2")
    @Column(name = "word_picture2")
    private String wordPicture2;

    /**
     * 单词图片地址3
     */
    @ApiModelProperty(value = "单词图片地址3")
    @Column(name = "word_picture3")
    private String wordPicture3;

    /**
     * 单词图片地址4
     */
    @ApiModelProperty(value = "单词图片地址4")
    @Column(name = "word_picture4")
    private String wordPicture4;

    /**
     * 音频
     */
    @ApiModelProperty(value = "音频")
    @Column(name = "audio")
    private String audio;
}
