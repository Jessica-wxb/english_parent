package com.tfjybj.english.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserNewpictureModel {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",required = true )
    private String userId;

    /**
     * 单词id
     */
    @ApiModelProperty(value = "单词id" )
    private String wordId;

    /**
     * 图片id
     */
    @ApiModelProperty(value = "图片id" )
    private String pictures;

}
