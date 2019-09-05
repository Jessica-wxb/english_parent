package com.tfjybj.english.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * Created by Administrator on 2019/8/18.
 */
@Data
public class NewPictureAddress {

    /**
     * Created by Administrator on 2019/8/17.
       */
        /**
         * 单词
         */
        @ApiModelProperty(value = "wordId")
        @Column(name = "wordId")
        public String wordId;

        /**
         * 单词
         */
        @ApiModelProperty(value = "单词")
        @Column(name = "word")
        private String word;

        /**
         * 新增单词图片地址
         */
        @ApiModelProperty(value = "新增单词图片地址")
        @Column(name = "picture_address")
        public String pictureAddress;
        /**
         * 用户id
         */
        @ApiModelProperty(value = "用户id",required = true )
        private String userId;

}
