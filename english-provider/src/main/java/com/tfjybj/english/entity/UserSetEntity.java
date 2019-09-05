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
 * UserSet实体
 * 用户设置
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "UserSetEntity:用户设置")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_user_set")
public class UserSetEntity extends BaseEntity implements Serializable {

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true)
	@Column(name = "user_id")
	private String userId;

	/**
	 * 单词播放次数
	 */
	@ApiModelProperty(value = "单词播放次数",required = true)
	@Column(name = "play_nums")
	private Integer playNums;

	/**
	 * 是否自动跳转0否，1是
	 */
	@ApiModelProperty(value = "是否自动跳转0否，1是",required = true)
	@Column(name = "is_turn_auto")
	private Integer isTurnAuto;

	/**
	 * 跳转延迟（ms）
	 */
	@ApiModelProperty(value = "跳转延迟（ms）",required = true)
	@Column(name = "turn_delay_time")
	private Integer turnDelayTime;

	/**
	 * 每次学习数量
	 */
	@ApiModelProperty(value = "每次学习数量",required = true)
	@Column(name = "study_number")
	private Integer studyNumber;


}
