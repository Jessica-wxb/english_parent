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
 * PhoneticWord实体
 * phoneticWord
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@ApiModel(value = "PhoneticWordEntity:phoneticWord")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonetic_word")
public class PhoneticWordEntity extends BaseEntity implements Serializable {

	/**
	 * 主键Id
	 */
    @ApiModelProperty(value = "主键Id")
	@Column(name = "phonetic")
	private String phonetic;

	/**
	 * 音标音频
	 */
    @ApiModelProperty(value = "音标音频")
	@Column(name = "phonetic_audio")
	private String phoneticAudio;

	/**
	 * 音标的正确单词发音
	 */
    @ApiModelProperty(value = "音标的正确单词发音")
	@Column(name = "correct_audio")
	private String correctAudio;

	/**
	 * 音标的正确单词图片
	 */
    @ApiModelProperty(value = "音标的正确单词图片")
	@Column(name = "correct_pic")
	private String correctPic;

	/**
	 * 音标的错误单词发音
	 */
    @ApiModelProperty(value = "音标的错误单词发音")
	@Column(name = "false_audio")
	private String falseAudio;

	/**
	 * 音标的错误单词图片
	 */
    @ApiModelProperty(value = "音标的错误单词图片")
	@Column(name = "false_pic")
	private String falsePic;


}
