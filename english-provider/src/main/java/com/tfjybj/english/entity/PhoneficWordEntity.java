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
 * @version .0.0.1
 * @since 2019-06-18 08:59:12
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
    @ApiModelProperty(value = "音标id")
	@Column(name = "phonefic_id")
	private String phoneficId;

	/**
	 * 音标
	 */
    @ApiModelProperty(value = "音标")
	@Column(name = "phonefic")
	private String phonefic;

	/**
	 * 音标音频
	 */
    @ApiModelProperty(value = "音标音频")
	@Column(name = "phonefic_audio")
	private String phoneficAudio;

	/**
	 * 正确单词
	 */
    @ApiModelProperty(value = "正确单词")
	@Column(name = "word_true")
	private String wordTrue;

	/**
	 * 正确单词图片
	 */
    @ApiModelProperty(value = "正确单词图片")
	@Column(name = "word_true_picture")
	private String wordTruePicture;

	/**
	 * 正确单词音频
	 */
    @ApiModelProperty(value = "正确单词音频")
	@Column(name = "word_true_audio")
	private String wordTrueAudio;

	/**
	 * 错误单词
	 */
    @ApiModelProperty(value = "错误单词")
	@Column(name = "word_false")
	private String wordFalse;

	/**
	 * 错误单词图片
	 */
    @ApiModelProperty(value = "错误单词图片")
	@Column(name = "word_false_picture")
	private String wordFalsePicture;

	/**
	 * 错误单词音频
	 */
    @ApiModelProperty(value = "错误单词音频")
	@Column(name = "word_false_audio")
	private String wordFalseAudio;

	/**
	 * 状态（单词正确与否）0正确；1错误
	 */
    @ApiModelProperty(value = "状态（单词正确与否）0正确；1错误")
	@Column(name = "state")
	private Integer state;


}
