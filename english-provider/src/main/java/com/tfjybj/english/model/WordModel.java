package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * WordModel
 * 单词
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "WordModel:单词")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class WordModel implements Serializable {

    //region 模板逆向生产来自WordEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "word主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 单词
	 */
	@ApiModelProperty(value = "单词",required = true )
	private String word;

	/**
	 * 单词图片地址1
	 */
	@ApiModelProperty(value = "单词图片地址1",required = true )
	private String wordPicture1;

	/**
	 * 单词图片地址2
	 */
	@ApiModelProperty(value = "单词图片地址2",required = true )
	private String wordPicture2;

	/**
	 * 单词图片地址3
	 */
	@ApiModelProperty(value = "单词图片地址3",required = true )
	private String wordPicture3;

	/**
	 * 单词图片地址4
	 */
	@ApiModelProperty(value = "单词图片地址4",required = true )
	private String wordPicture4;

	/**
	 * 单词图片地址5
	 */
	@ApiModelProperty(value = "单词图片地址5",required = true )
	private String wordPicture5;

	/**
	 * 音频
	 */
	@ApiModelProperty(value = "音频",required = true )
	private String audio;

	/**
	 * 单词的发音
	 */
	@ApiModelProperty(value = "单词的发音",required = true )
	private String phonefic;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
