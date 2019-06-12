package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * UserWrongModel
 * 错题本
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "UserWrongModel:错题本")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class UserWrongModel implements Serializable {

    //region 模板逆向生产来自UserWrongEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "userWrong主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true )
	private String userId;

	/**
	 * 音标id
	 */
	@ApiModelProperty(value = "音标id",required = true )
	private String phoneficId;

	/**
	 * 错误音标
	 */
    @ApiModelProperty(value = "错误音标" )
	private String phonefic;

	/**
	 * 单词id
	 */
	@ApiModelProperty(value = "单词id",required = true )
	private String wordId;

	/**
	 * 错误单词
	 */
    @ApiModelProperty(value = "错误单词" )
	private String word;

	/**
	 * 状态 ：1，单词  ；2，音标
	 */
	@ApiModelProperty(value = "状态 ：1，单词  ；2，音标",required = true  ,example="0" )
	private Integer type;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
