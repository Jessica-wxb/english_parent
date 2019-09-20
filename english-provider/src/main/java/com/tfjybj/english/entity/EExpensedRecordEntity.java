package com.tfjybj.english.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * EExpensedRecord实体
 * eExpensedRecord
 *
 * @author 王小波 
 * @version ${version}
 * @since ${version} 2019-09-20 16:05:03
 */
@ApiModel(value = "EExpensedRecordEntity:eExpensedRecord")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_e_expensed_record")
public class EExpensedRecordEntity extends BaseEntity implements Serializable {

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true)
	@Column(name = "user_id")
	private String userId;

	/**
	 * 描述用户消费的类型：积分兑换：个数、宠物兑换：类型（dog）
	 */
	@ApiModelProperty(value = "描述用户消费的类型：积分兑换：个数、宠物兑换：类型（dog）",required = true)
	@Column(name = "description")
	private String description;

	/**
	 * 消费的E币数
	 */
	@ApiModelProperty(value = "消费的E币数",required = true)
	@Column(name = "expensed_e_num")
	private String expensedENum;


}
