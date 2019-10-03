package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PhoneticWrongModel
 * 音标错题
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@ApiModel(value = "PhoneticWrongModel:音标错题")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneticWrongModel implements Serializable {

    //region 模板逆向生产来自PhoneticWrongEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "phoneticWrong主键")
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
	 * 是否归仓（0表示未归仓  1表示已归仓）
	 */
    @ApiModelProperty(value = "是否归仓（0表示未归仓  1表示已归仓）")
	private Integer isStore;

	/**
	 * 来源（0代表单词选音标 1代表音标选单词）
	 */
    @ApiModelProperty(value = "来源（0代表单词选音标 1代表音标选单词）")
	private Integer falseType;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
