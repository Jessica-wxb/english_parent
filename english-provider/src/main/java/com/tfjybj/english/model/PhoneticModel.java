package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PhoneticModel
 * phonetic
 *
 * @author 白靖 
 * @version ${version}
 * @since ${version} 2019-09-05 17:36:14
 */
@ApiModel(value = "PhoneticModel:phonetic")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneticModel implements Serializable {

    //region 模板逆向生产来自PhoneticEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "phonetic主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 主键Id
	 */
    @ApiModelProperty(value = "主键Id")
	private String phonetic;

	/**
	 * 音标音频
	 */
    @ApiModelProperty(value = "音标音频")
	private String phoneticAudio;

	/**
	 * 音标图片
	 */
    @ApiModelProperty(value = "音标图片")
	private String phoneticPic;

	/**
	 * 音标视频
	 */
    @ApiModelProperty(value = "音标视频")
	private String video;

	/**
	 * 发音诀窍
	 */
    @ApiModelProperty(value = "发音诀窍")
	private String knack;

	/**
	 * 单词音频1
	 */
    @ApiModelProperty(value = "单词音频1")
	private String wordAudio1;

	/**
	 * 单词音频2
	 */
    @ApiModelProperty(value = "单词音频2")
	private String wordAudio2;

	/**
	 * 单词音频3
	 */
    @ApiModelProperty(value = "单词音频3")
	private String wordAudio3;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
