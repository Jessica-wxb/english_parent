package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PhoneficWrongModel
 * 音标错题
 *
 * @author 张凯超 
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "PhoneficWrongModel:音标错题")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneficWrongModel implements Serializable {

    //region 模板逆向生产来自PhoneficWrongEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "phoneficWrong主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 用户id
	 */
    @ApiModelProperty(value = "用户id" )
	private String userId;

	/**
	 * 音标
	 */
    @ApiModelProperty(value = "音标" )
	private String phonefic;

	/**
	 * 是否检测过，未检出0 错误1 正确2
	 */
    @ApiModelProperty(value = "是否检测过，未检出0 错误1 正确2"  ,example="0" )
	private Integer status;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
