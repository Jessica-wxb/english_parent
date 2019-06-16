package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

/**
 * UserRecordModel
 * 用户记录
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "UserRecordModel:用户记录")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class UserRecordModel implements Serializable {

    //region 模板逆向生产来自UserRecordEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "userRecord主键")
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
	@ApiModelProperty(value = "单词id",required = true )
	private String wordId;

	/**
	 * 已学单词
	 */
    @ApiModelProperty(value = "已学单词" )
	private String word;

	/**
	 * 音标id
	 */
	@ApiModelProperty(value = "音标id",required = true )
	private String phoneficId;

	/**
	 * 已学音标
	 */
    @ApiModelProperty(value = "已学音标" )
	private String phonefic;

	/**
	 * 状态 ：1，单词  ；2，音标
	 */
	@ApiModelProperty(value = "状态 ：1，单词  ；2，音标",required = true  ,example="0" )
	private Integer type;

	/**
	 * 结果：正确、错误
	 */
	@ApiModelProperty(value = "结果：正确、错误",required = true  ,example="0" )
	private Integer status;

	//endregion

     /* *****************************以下是非模板生成的内容************************************ */

	/**
	 * 创建时间字段_冯佳兴_2019年6月15日14:35:28
	 */
	@ApiModelProperty(value="创建时间",required = true)
	private Date createTime;
}
