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
 * PhoneficWord实体
 * 据音标选单词
 *
 * @author 马莹 
 * @version 1.0.0
 * @since 2019-06-14 21:24:30
 */
@ApiModel(value = "PhoneficWordEntity:据音标选单词")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonefic_word")
public class PhoneficWordEntity extends BaseEntity implements Serializable {

	/**
	 * 音标id
	 */
	@ApiModelProperty(value = "音标id",required = true)
	@Column(name = "phonefic_id")
	private String phoneficId;

	/**
	 * 音标音频
	 */
    @ApiModelProperty(value = "音标音频")
	@Column(name = "phonefic_audio")
	private String phoneficAudio;

	/**
	 * 单词正确图片
	 */
    @ApiModelProperty(value = "单词正确图片")
	@Column(name = "word_true_picture")
	private String wordTruePicture;

	/**
	 * 单词错误图片
	 */
    @ApiModelProperty(value = "单词错误图片")
	@Column(name = "word_false_picture")
	private String wordFalsePicture;

	/**
	 * 单词正确音频
	 */
    @ApiModelProperty(value = "单词正确音频")
	@Column(name = "word_true_audio")
	private String wordTrueAudio;

	/**
	 * 单词错误音频
	 */
    @ApiModelProperty(value = "单词错误音频")
	@Column(name = "word_false_audio")
	private String wordFalseAudio;

	/**
	 * 单词
	 */
    @ApiModelProperty(value = "单词")
	@Column(name = "word")
	private String word;

	/**
	 * 状态（单词正确与否）0正确；1错误
	 */
    @ApiModelProperty(value = "状态（单词正确与否）0正确；1错误")
	@Column(name = "state")
	private Integer state;


}
