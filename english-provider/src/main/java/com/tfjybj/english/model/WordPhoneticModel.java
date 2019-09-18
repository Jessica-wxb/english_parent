package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * WordPhoneticModel
 * wordPhonetic
 *
 * @author 闫伟强 
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@ApiModel(value = "WordPhoneticModel:wordPhonetic")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class WordPhoneticModel implements Serializable {

    //region 模板逆向生产来自WordPhoneticEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "wordPhonetic主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 主键Id
	 */
    @ApiModelProperty(value = "主键Id")
	private String phonetic;

	/**
	 * 单词音频
	 */
    @ApiModelProperty(value = "单词音频")
	private String wordAudio;

	/**
	 * 单词的正确音标发音
	 */
    @ApiModelProperty(value = "单词的正确音标发音")
	private String correctAudio;

	/**
	 * 单词的正确音标图片
	 */
    @ApiModelProperty(value = "单词的正确音标图片")
	private String correctPic;

	/**
	 * 单词的错误音标发音
	 */
    @ApiModelProperty(value = "单词的错误音标发音")
	private String falseAudio;

	/**
	 * 单词的错误音标图片
	 */
    @ApiModelProperty(value = "单词的错误音标图片")
	private String falsePic;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
