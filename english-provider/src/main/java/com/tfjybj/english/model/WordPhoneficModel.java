package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import javax.persistence.Column;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * WordPhoneficModel
 * 据单词选音标
 *
 * @author 马莹
 * @version 1.0.0
 * @since 2019-06-14 21:24:30
 */
@ApiModel(value = "WordPhoneficModel:据单词选音标")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class WordPhoneficModel implements Serializable {

    //region 模板逆向生产来自WordPhoneficEntity的属性
    /**
     * id
     */
    @ApiModelProperty(value = "wordPhonefic主键")
    @Column(name = "id")
    private String id;
    /**
     * 单词id
     */
    @ApiModelProperty(value = "单词id")
    private String wordId;

    /**
     * 单词
     */
    @ApiModelProperty(value = "单词发音")
    private String wordAudio;

    /**
     * 音标正确id
     */
    @ApiModelProperty(value = "音标正确id")
    private String phoneficTrueId;

    /**
     * 音标错误id
     */
    @ApiModelProperty(value = "音标错误id")
    private String phoneficFalseId;

    /**
     * 音标音频
     */
    @ApiModelProperty(value = "音标音频")
    private String phoneficTrueAudio;

    /**
     * 音标错误音频
     */
    @ApiModelProperty(value = "音标错误音频")
    private String phoneficFalseAudio;

    /**
     * 音标正确图片
     */
    @ApiModelProperty(value = "音标正确图片")
    private String phoneficTruePicture;

    /**
     * 音标错误图片
     */
    @ApiModelProperty(value = "音标错误图片")
    private String phoneficFalsePicture;

    /**
     * 状态（音标正确与否）
     */
    @ApiModelProperty(value = "状态（音标正确与否）", example = "0")
    private Integer state;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间", required = true)
    private Date creatTime;

    //endregion

    /* *****************************以下是非模板生成的内容************************************ */
}
