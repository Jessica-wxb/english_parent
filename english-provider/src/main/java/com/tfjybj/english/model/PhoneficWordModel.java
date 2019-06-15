package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PhoneficWordModel
 * 据音标选单词
 *
 * @author 马莹 
 * @version 1.0.0
 * @since 2019-06-14 21:24:30
 */
@ApiModel(value = "PhoneficWordModel:据音标选单词")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneficWordModel implements Serializable {

    //region 模板逆向生产来自PhoneficWordEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "phoneficWord主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 音标id
	 */
	@ApiModelProperty(value = "音标id",required = true )
	private String phoneficId;

	/**
	 * 音标音频
	 */
    @ApiModelProperty(value = "音标音频" )
	private String phoneficAudio;

	/**
	 * 单词正确图片
	 */
    @ApiModelProperty(value = "单词正确图片" )
	private String wordTruePicture;

	/**
	 * 单词错误图片
	 */
    @ApiModelProperty(value = "单词错误图片" )
	private String wordFalsePicture;

	/**
	 * 单词正确音频
	 */
    @ApiModelProperty(value = "单词正确音频" )
	private String wordTrueAudio;

	/**
	 * 单词错误音频
	 */
    @ApiModelProperty(value = "单词错误音频" )
	private String wordFalseAudio;

	/**
	 * 单词
	 */
    @ApiModelProperty(value = "单词" )
	private String word;

	/**
	 * 状态（单词正确与否）0正确；1错误
	 */
    @ApiModelProperty(value = "状态（单词正确与否）0正确；1错误"  ,example="0" )
	private Integer state;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
