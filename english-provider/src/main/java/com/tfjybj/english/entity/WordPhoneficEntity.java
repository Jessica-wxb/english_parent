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
 * WordPhonefic实体
 * 据单词选音标
 *
 * @author 马莹
 * @version .0.0.1
 * @since 2019-06-18 08:59:12
 */
@ApiModel(value = "WordPhoneficEntity:据单词选音标")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_word_phonefic")
public class WordPhoneficEntity extends BaseEntity implements Serializable {

    /**
     * 单词id
     */
    @ApiModelProperty(value = "单词id")
    @Column(name = "word_id")
    private String wordId;

    /**
     * 单词
     */
    @ApiModelProperty(value = "单词")
    @Column(name = "word")
    private String word;

    /**
     * 单词音频
     */
    @ApiModelProperty(value = "单词音频")
    @Column(name = "word_audio")
    private String wordAudio;

    /**
     * 正确音标
     */
    @ApiModelProperty(value = "正确音标")
    @Column(name = "phonefic_true")
    private String phoneficTrue;

    /**
     * 音标正确id
     */
    @ApiModelProperty(value = "音标正确id")
    @Column(name = "phonefic_true_id")
    private String phoneficTrueId;

    /**
     * 错误音标
     */
    @ApiModelProperty(value = "错误音标")
    @Column(name = "phonefic_false")
    private String phoneficFalse;

    /**
     * 音标错误id
     */
    @ApiModelProperty(value = "音标错误id")
    @Column(name = "phonefic_false_id")
    private String phoneficFalseId;

    /**
     * 音标音频
     */
    @ApiModelProperty(value = "音标音频")
    @Column(name = "phonefic_true_audio")
    private String phoneficTrueAudio;

    /**
     * 音标错误音频
     */
    @ApiModelProperty(value = "音标错误音频")
    @Column(name = "phonefic_false_audio")
    private String phoneficFalseAudio;

    /**
     * 音标正确图片
     */
    @ApiModelProperty(value = "音标正确图片")
    @Column(name = "phonefic_true_picture")
    private String phoneficTruePicture;

    /**
     * 音标错误图片
     */
    @ApiModelProperty(value = "音标错误图片")
    @Column(name = "phonefic_false_picture")
    private String phoneficFalsePicture;

    /**
     * 状态（音标正确与否）
     */
    @ApiModelProperty(value = "状态（音标正确与否）")
    @Column(name = "state")
    private Integer state;


}
