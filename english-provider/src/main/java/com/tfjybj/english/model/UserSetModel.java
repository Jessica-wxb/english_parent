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
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
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
	 * 单词播放次数
	 */
	@ApiModelProperty(value = "单词播放次数",required = true  ,example="0" )
	private Integer playNums;

	/**
	 * 是否自动跳转0否，1是
	 */
	@ApiModelProperty(value = "是否自动跳转0否，1是",required = true  ,example="0" )
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

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
