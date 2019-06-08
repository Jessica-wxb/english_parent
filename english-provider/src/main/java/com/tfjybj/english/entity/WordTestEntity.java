package com.tfjybj.english.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * WordTest实体
 * 单词练习
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "WordTestEntity:单词练习")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_word_test")
public class WordTestEntity extends BaseEntity implements Serializable {

	/**
	 * 单词id
	 */
	@ApiModelProperty(value = "单词id",required = true)
	@Column(name = "word_id")
	private String wordId;

	/**
	 * 单词
	 */
    @ApiModelProperty(value = "单词")
	@Column(name = "word")
	private String word;

	/**
	 * 音标id
	 */
	@ApiModelProperty(value = "音标id",required = true)
	@Column(name = "phonefic_id")
	private String phoneficId;

	/**
	 * 音标
	 */
    @ApiModelProperty(value = "音标")
	@Column(name = "phonefic")
	private String phonefic;

	/**
	 * 状态（音标正确与否）
	 */
	@ApiModelProperty(value = "状态（音标正确与否）",required = true)
	@Column(name = "state")
	private Integer state;

	/**
	 * 创建时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "创建时间",required = true)
	@Column(name = "creat_time")
	private Date creatTime;


}
