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
 * Phonetic实体
 * phonetic
 *
 * @author 白靖 
 * @version ${version}
 * @since ${version} 2019-09-05 17:36:14
 */
@ApiModel(value = "PhoneticEntity:phonetic")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonetic")
public class PhoneticEntity extends BaseEntity implements Serializable {

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
	 * 音标图片
	 */
    @ApiModelProperty(value = "音标图片")
	@Column(name = "phonetic_pic")
	private String phoneticPic;

	/**
	 * 音标视频
	 */
    @ApiModelProperty(value = "音标视频")
	@Column(name = "video")
	private String video;

	/**
	 * 发音诀窍
	 */
    @ApiModelProperty(value = "发音诀窍")
	@Column(name = "knack")
	private String knack;

	/**
	 * 单词音频1
	 */
    @ApiModelProperty(value = "单词音频1")
	@Column(name = "word_audio1")
	private String wordAudio1;

	/**
	 * 单词音频2
	 */
    @ApiModelProperty(value = "单词音频2")
	@Column(name = "word_audio2")
	private String wordAudio2;

	/**
	 * 单词音频3
	 */
    @ApiModelProperty(value = "单词音频3")
	@Column(name = "word_audio3")
	private String wordAudio3;


}
