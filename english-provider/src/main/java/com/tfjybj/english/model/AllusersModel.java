package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * AllusersModel
 * 用户表
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 17:38:43
 */
@ApiModel(value = "AllusersModel:用户表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class AllusersModel implements Serializable {

    //region 模板逆向生产来自AllusersEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "allusers主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 钉钉id
	 */
    @ApiModelProperty(value = "钉钉id" )
	private String dingId;

	/**
	 * 用户编码
	 */
    @ApiModelProperty(value = "用户编码" )
	private String userCode;

	/**
	 * 密码
	 */
    @ApiModelProperty(value = "密码" )
	private String password;

	/**
	 * 用户姓名
	 */
    @ApiModelProperty(value = "用户姓名" )
	private String userName;

	/**
	 * 学校编号
	 */
    @ApiModelProperty(value = "学校编号" )
	private String schoolNo;

	/**
	 * qq登录唯一标示
	 */
    @ApiModelProperty(value = "qq登录唯一标示" )
	private String qqOpenId;

	/**
	 * 微信统一标识
	 */
    @ApiModelProperty(value = "微信统一标识" )
	private String unionid;

	/**
	 * 微信统一标识是否有效（0/1 有效/无效）
	 */
    @ApiModelProperty(value = "微信统一标识是否有效（0/1 有效/无效）"  ,example="0" )
	private Integer unionidValid;

	/**
	 * 电子邮件
	 */
    @ApiModelProperty(value = "电子邮件" )
	private String email;

	/**
	 * 微信公众号唯一标识
	 */
    @ApiModelProperty(value = "微信公众号唯一标识" )
	private String wxPlantForm;

	/**
	 * 微信公众号唯一标识是否有效（0/1 有效/无效）
	 */
    @ApiModelProperty(value = "微信公众号唯一标识是否有效（0/1 有效/无效）"  ,example="0" )
	private Integer wxPlantValid;

	/**
	 * 移动电话
	 */
    @ApiModelProperty(value = "移动电话" )
	private String telNum;

	/**
	 * 是否锁定(0/1 未锁定/锁定)
	 */
    @ApiModelProperty(value = "是否锁定(0/1 未锁定/锁定)"  ,example="0" )
	private Integer isLock;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
