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
 * UserRecord实体
 * 用户记录
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "UserRecordEntity:用户记录")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_user_record")
public class UserRecordEntity extends BaseEntity implements Serializable {

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true)
	@Column(name = "user_id")
	private String userId;

	/**
	 * 单词id
	 */
	@ApiModelProperty(value = "单词id",required = true)
	@Column(name = "word_id")
	private String wordId;

	/**
	 * 已学单词
	 */
    @ApiModelProperty(value = "已学单词")
	@Column(name = "word")
	private String word;

	/**
	 * 音标id
	 */
	@ApiModelProperty(value = "音标id",required = true)
	@Column(name = "phonefic_id")
	private String phoneficId;

	/**
	 * 已学音标
	 */
    @ApiModelProperty(value = "已学音标")
	@Column(name = "phonefic")
	private String phonefic;

	/**
	 * 状态 ：1，单词  ；2，音标
	 */
	@ApiModelProperty(value = "状态 ：1，单词  ；2，音标",required = true)
	@Column(name = "type")
	private Integer type;

	/**
	 * 结果：正确、错误
	 */
	@ApiModelProperty(value = "结果：正确、错误",required = true)
	@Column(name = "status")
	private Integer status;

	/**
	 * 单词音频
	 */
//	@ApiModelProperty(value = "单词音频",required = true)
//	@Column(name = "audio")
//	private String audio;

	/**
	 * 单词的第一张图片
	 */
//	@ApiModelProperty(value = "单词的第一张图片",required = true)
//	@Column(name = "word_picture1")
//	private String wordPicture1;
}
