package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * UserModel
 * 用户
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "UserModel:用户")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class UserModel implements Serializable {

    //region 模板逆向生产来自UserEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "user主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true )
	private String userId;

	/**
	 * 用户code
	 */
	@ApiModelProperty(value = "用户code",required = true )
	private String userCode;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名",required = true )
	private String userName;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
