package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PhoneticRecordModel
 * 音标学习记录
 *
 * @author 闫伟强 
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@ApiModel(value = "PhoneticRecordModel:音标学习记录")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneticRecordModel implements Serializable {

    //region 模板逆向生产来自PhoneticRecordEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "phoneticRecord主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 用户id
	 */
    @ApiModelProperty(value = "用户id")
	private String userId;

	/**
	 * 音标
	 */
    @ApiModelProperty(value = "音标")
	private String phonetic;

	/**
	 * 是否检测过，未检出0 错误1 正确2
	 */
    @ApiModelProperty(value = "是否检测过，未检出0 错误1 正确2")
	private Integer isCheck;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
