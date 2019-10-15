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
 * WordPhonetic实体
 * wordPhonetic
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@ApiModel(value = "WordPhoneticEntity:wordPhonetic")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_word_phonetic")
public class WordPhoneticEntity extends BaseEntity implements Serializable {

	/**
	 * 主键Id
	 */
    @ApiModelProperty(value = "主键Id")
	@Column(name = "phonetic")
	private String phonetic;

	/**
	 * 单词音频
	 */
    @ApiModelProperty(value = "单词音频")
	@Column(name = "word_audio")
	private String wordAudio;

	/**
	 * 单词的正确音标发音
	 */
    @ApiModelProperty(value = "单词的正确音标发音")
	@Column(name = "correct_audio")
	private String correctAudio;

	/**
	 * 单词的正确音标图片
	 */
    @ApiModelProperty(value = "单词的正确音标图片")
	@Column(name = "correct_pic")
	private String correctPic;

	/**
	 * 单词的错误音标发音
	 */
    @ApiModelProperty(value = "单词的错误音标发音")
	@Column(name = "false_audio")
	private String falseAudio;

	/**
	 * 单词的错误音标图片
	 */
    @ApiModelProperty(value = "单词的错误音标图片")
	@Column(name = "false_pic")
	private String falsePic;


}
