package com.tfjybj.english.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * Word实体
 * 单词
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "WordEntity:单词")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_word")
public class WordEntity extends BaseEntity implements Serializable {

    /**
     * 单词
     */
    @ApiModelProperty(value = "单词", required = true)
    @Column(name = "word")
    private String word;

    /**
     * 单词图片地址1
     */
    @ApiModelProperty(value = "单词图片地址1", required = true)
    @Column(name = "word_picture1")
    private String wordPicture1;

    /**
     * 单词图片地址2
     */
    @ApiModelProperty(value = "单词图片地址2", required = true)
    @Column(name = "word_picture2")
    private String wordPicture2;

    /**
     * 单词图片地址3
     */
    @ApiModelProperty(value = "单词图片地址3", required = true)
    @Column(name = "word_picture3")
    private String wordPicture3;

    /**
     * 单词图片地址4
     */
    @ApiModelProperty(value = "单词图片地址4", required = true)
    @Column(name = "word_picture4")
    private String wordPicture4;

    /**
     * 单词图片地址5
     */
    @ApiModelProperty(value = "单词图片地址5", required = true)
    @Column(name = "word_picture5")
    private String wordPicture5;

    /**
     * 音频
     */
    @ApiModelProperty(value = "音频", required = true)
    @Column(name = "audio")
    private String audio;

    /**
     * 单词的发音
     */
    @ApiModelProperty(value = "单词的发音", required = true)
    @Column(name = "phonefic")
    private String phonefic;


}
