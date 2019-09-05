package com.tfjybj.english.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPartModel {


    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    /**
     * 用户坚持天数
     */
    @ApiModelProperty(value = "用户坚持天数",required = true)
    private String insistDays;

    /**
     * 单词学习数量
     */
    @ApiModelProperty(value = "单词学习数量",required = true)
    private String wordNum;

    /**
     * 获得E币总数量
     */
    @ApiModelProperty(value = "获得E币总数量",required = true)
    private String eAllNum;

    /**
     * 当前持有E币数量
     */
    @ApiModelProperty(value = "当前持有E币数量",required = true)
    private String eNowNum;


    /**
     * 单词播放次数
     */
    @ApiModelProperty(value = "单词播放次数",required = true)
    private Integer playNums;

    /**
     * 是否自动跳转0否，1是
     */
    @ApiModelProperty(value = "是否自动跳转0否，1是",required = true)
    private Integer isTurnAuto;

    /**
     * 跳转延迟（ms）
     */
    @ApiModelProperty(value = "跳转延迟（ms）",required = true)
    private Integer turnDelayTime;

    /**
     * 每次学习数量
     */
    @ApiModelProperty(value = "每次学习数量",required = true)
    private Integer studyNumber;

}
