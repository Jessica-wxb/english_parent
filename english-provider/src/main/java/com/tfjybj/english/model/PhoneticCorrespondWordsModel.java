package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PhoneticCorrespondWordsModel
 * 音标对应的单词表
 *
 * @author 马莹 
 * @version 1.0.0
 * @since 2019-06-14 16:48:08
 */
@ApiModel(value = "PhoneticCorrespondWordsModel:音标对应的单词表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneticCorrespondWordsModel implements Serializable {

    //region 模板逆向生产来自PhoneticCorrespondWordsEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "phoneticCorrespondWords主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 单词全拼
	 */
    @ApiModelProperty(value = "单词全拼" )
	private String word;

	/**
	 * 音标id
	 */
    @ApiModelProperty(value = "音标id" )
	private String phoneficId;

	/**
	 * 音频地址
	 */
    @ApiModelProperty(value = "音频地址" )
	private String audio;

	/**
	 * y:元音,f:辅音
	 */
    @ApiModelProperty(value = "y:元音,f:辅音" )
	private String vowelOrConsonant;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
