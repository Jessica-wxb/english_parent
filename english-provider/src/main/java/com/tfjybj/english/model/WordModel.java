package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * WordModel
 * 单词表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "WordModel:单词表")
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
    @ApiModelProperty(value = "单词" )
	private String word;

	/**
	 * 单词数量
	 */
	@ApiModelProperty(value = "单词数量" )
	private Integer countWord;

	/**
	 * 单词图片地址1
	 */
    @ApiModelProperty(value = "单词图片地址1" )
	private String wordPicture1;

	/**
	 * 单词图片地址2
	 */
    @ApiModelProperty(value = "单词图片地址2" )
	private String wordPicture2;

	/**
	 * 单词图片地址3
	 */
    @ApiModelProperty(value = "单词图片地址3" )
	private String wordPicture3;

	/**
	 * 单词图片地址4
	 */
    @ApiModelProperty(value = "单词图片地址4" )
	private String wordPicture4;

	/**
	 * 单词图片地址5
	 */
    @ApiModelProperty(value = "单词图片地址5" )
	private String wordPicture5;

	/**
	 * 音频
	 */
    @ApiModelProperty(value = "音频" )
	private String audio;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */

}
