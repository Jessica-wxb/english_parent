package com.tfjybj.english.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import javax.persistence.Column;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * PhoneficTest实体
 * 音标练习
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "PhoneficTestEntity:音标练习")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonefic_test")
public class PhoneficTestEntity extends BaseEntity implements Serializable {

    /**
     * 音标id
     */
    @ApiModelProperty(value = "音标id", required = true)
    @Column(name = "phonefic_id")
    private String phoneficId;

    /**
     * 音标
     */
    @ApiModelProperty(value = "音标")
    @Column(name = "phonefic")
    private String phonefic;

    /**
     * 单词id
     */
    @ApiModelProperty(value = "单词id", required = true)
    @Column(name = "word_id")
    private String wordId;

    /**
     * 单词
     */
    @ApiModelProperty(value = "单词")
    @Column(name = "word")
    private String word;

    /**
     * 状态（单词正确与否）
     */
    @ApiModelProperty(value = "状态（单词正确与否）", required = true)
    @Column(name = "state")
    private Integer state;

}
