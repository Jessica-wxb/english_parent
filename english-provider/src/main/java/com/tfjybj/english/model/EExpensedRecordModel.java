package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * EExpensedRecordModel
 * eExpensedRecord
 *
 * @author 王小波 
 * @version ${version}
 * @since ${version} 2019-09-20 16:05:03
 */
@ApiModel(value = "EExpensedRecordModel:eExpensedRecord")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class EExpensedRecordModel implements Serializable {

    //region 模板逆向生产来自EExpensedRecordEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "eExpensedRecord主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true)
	private String userId;

	/**
	 * 描述用户消费的类型：积分兑换：个数、宠物兑换：类型（dog）
	 */
	@ApiModelProperty(value = "描述用户消费的类型：积分兑换：个数、宠物兑换：类型（dog）",required = true)
	private String description;

	/**
	 * 消费的E币数
	 */
	@ApiModelProperty(value = "消费的E币数",required = true)
	private String expensedENum;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
