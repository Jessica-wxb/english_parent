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
 * PhoneticRecord实体
 * 音标学习记录
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@ApiModel(value = "PhoneticRecordEntity:音标学习记录")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonetic_record")
public class PhoneticRecordEntity extends BaseEntity implements Serializable {

	public PhoneticRecordEntity(String id,String userId,String phonetic,Integer isCheck ){
		this.id=id;
		this.userId=userId;
		this.phonetic=phonetic;
		this.isCheck=isCheck;
	}
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
	@Column(name = "phonetic")
	private String phonetic;

	/**
	 * 是否检测过，未检出0 错误1 正确2
	 */
    @ApiModelProperty(value = "是否检测过，未检出0 错误1 正确2")
	@Column(name = "is_check")
	private Integer isCheck;


}
