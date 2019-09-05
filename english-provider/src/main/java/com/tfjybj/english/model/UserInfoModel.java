package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * UserInfoModel
 * 用户id
 *
 * @author 张凯超 
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "UserInfoModel:用户id")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class UserInfoModel implements Serializable {

    //region 模板逆向生产来自UserInfoEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "userInfo主键")
	@Column(name = "id")
	private String id;
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

	/**
	 * 用户坚持天数
	 */
	@ApiModelProperty(value = "用户坚持天数",required = true )
	private String insistDays;

	/**
	 * 单词学习数量
	 */
	@ApiModelProperty(value = "单词学习数量",required = true )
	private String wordNum;

	/**
	 * 获得E币总数量
	 */
	@ApiModelProperty(value = "获得E币总数量",required = true )
	private String eAllNum;

	/**
	 * 当前持有E币数量
	 */
	@ApiModelProperty(value = "当前持有E币数量",required = true )
	private String eNowNum;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */

	@Override
	public String toString() {
		return  "{\"password\": \"15732677791\",\"userCode\": \"15732677791\"}";
	}
}
