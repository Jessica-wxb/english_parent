package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PhoneticWordModel
 * phoneticWord
 *
 * @author 闫伟强 
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@ApiModel(value = "PhoneticWordModel:phoneticWord")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneticWordModel implements Serializable {

    //region 模板逆向生产来自PhoneticWordEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "phoneticWord主键")
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
	 * 音标的正确单词发音
	 */
    @ApiModelProperty(value = "音标的正确单词发音")
	private String correctAudio;

	/**
	 * 音标的正确单词图片
	 */
    @ApiModelProperty(value = "音标的正确单词图片")
	private String correctPic;

	/**
	 * 音标的错误单词发音
	 */
    @ApiModelProperty(value = "音标的错误单词发音")
	private String falseAudio;

	/**
	 * 音标的错误单词图片
	 */
    @ApiModelProperty(value = "音标的错误单词图片")
	private String falsePic;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
