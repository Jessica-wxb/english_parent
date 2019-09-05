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
 * PhoneficWrong实体
 * 音标错题
 *
 * @author 张凯超 
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "PhoneficWrongEntity:音标错题")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonefic_wrong")
public class PhoneficWrongEntity extends BaseEntity implements Serializable {

	/**
	 * 用户id
	 */
    @ApiModelProperty(value = "用户id")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 音标
	 */
    @ApiModelProperty(value = "音标")
	@Column(name = "phonefic")
	private String phonefic;

	/**
	 * 是否检测过，未检出0 错误1 正确2
	 */
    @ApiModelProperty(value = "是否检测过，未检出0 错误1 正确2")
	@Column(name = "status")
	private Integer status;


}
