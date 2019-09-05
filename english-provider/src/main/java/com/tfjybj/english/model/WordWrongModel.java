package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * WordWrongModel
 * 单词错题
 *
 * @author 张凯超 
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "WordWrongModel:单词错题")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class WordWrongModel implements Serializable {

    //region 模板逆向生产来自WordWrongEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "wordWrong主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true )
	private String userId;

	/**
	 * 单词id
	 */
    @ApiModelProperty(value = "单词id" )
	private String wordId;

	/**
	 * 是否学习 0，未学习  ；1，已学习
	 */
	@ApiModelProperty(value = "是否学习 0，未学习  ；1，已学习",required = true  ,example="0" )
	private Integer isStudy;

	/**
	 * 是否检测正确 0错误 ；1正确
	 */
	@ApiModelProperty(value = "是否检测正确 0错误 ；1正确",required = true  ,example="0" )
	private Integer isCheck;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
