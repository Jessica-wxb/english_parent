package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * UserSetModel
 * 用户设置
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "UserSetModel:用户设置")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class UserSetModel implements Serializable {

    //region 模板逆向生产来自UserSetEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "userSet主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true )
	private String userId;

	/**
	 * 音标次数
	 */
	@ApiModelProperty(value = "音标次数",required = true  ,example="0" )
	private Integer phoneficNumber;

	/**
	 * 单词次数
	 */
	@ApiModelProperty(value = "单词次数",required = true  ,example="0" )
	private Integer wordNumber;

	/**
	 * 是否自动跳转
	 */
	@ApiModelProperty(value = "是否自动跳转",required = true  ,example="0" )
	private Integer isTurnAuto;

	/**
	 * 跳转延迟（ms）
	 */
	@ApiModelProperty(value = "跳转延迟（ms）",required = true  ,example="0" )
	private Integer turnDelayTime;

	/**
	 * 每次学习数量
	 */
	@ApiModelProperty(value = "每次学习数量",required = true  ,example="0" )
	private Integer studyNumber;

	/**
	 * 是否随机选词
	 */
	@ApiModelProperty(value = "是否随机选词",required = true  ,example="0" )
	private Integer isRandom;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
