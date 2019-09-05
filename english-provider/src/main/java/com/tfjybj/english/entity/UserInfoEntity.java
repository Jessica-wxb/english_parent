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
 * UserInfo实体
 * 用户id
 *
 * @author 张凯超 
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "UserInfoEntity:用户id")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_user_info")
public class UserInfoEntity extends BaseEntity implements Serializable {

	/**
	 * 用户code
	 */
	@ApiModelProperty(value = "用户code",required = true)
	@Column(name = "user_code")
	private String userCode;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名",required = true)
	@Column(name = "user_name")
	private String userName;

	/**
	 * 用户坚持天数
	 */
	@ApiModelProperty(value = "用户坚持天数",required = true)
	@Column(name = "insist_days")
	private String insistDays;

	/**
	 * 单词学习数量
	 */
	@ApiModelProperty(value = "单词学习数量",required = true)
	@Column(name = "word_num")
	private String wordNum;

	/**
	 * 获得E币总数量
	 */
	@ApiModelProperty(value = "获得E币总数量",required = true)
	@Column(name = "e_all_num")
	private String eAllNum;

	/**
	 * 当前持有E币数量
	 */
	@ApiModelProperty(value = "当前持有E币数量",required = true)
	@Column(name = "e_now_num")
	private String eNowNum;


}
