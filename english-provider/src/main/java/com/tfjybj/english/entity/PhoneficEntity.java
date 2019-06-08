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
 * Phonefic实体
 * 音标
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "PhoneficEntity:音标")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonefic")
public class PhoneficEntity extends BaseEntity implements Serializable {

	/**
	 * 音标
	 */
	@ApiModelProperty(value = "音标",required = true)
	@Column(name = "phonefic")
	private String phonefic;

	/**
	 * 音标发音图片-地址
	 */
	@ApiModelProperty(value = "音标发音图片-地址",required = true)
	@Column(name = "phonefic_picture")
	private String phoneficPicture;

	/**
	 * 音标诀窍图片-地址
	 */
	@ApiModelProperty(value = "音标诀窍图片-地址",required = true)
	@Column(name = "knack_picture")
	private String knackPicture;

	/**
	 * 音频
	 */
	@ApiModelProperty(value = "音频",required = true)
	@Column(name = "audio")
	private String audio;

	/**
	 * 视频
	 */
	@ApiModelProperty(value = "视频",required = true)
	@Column(name = "video")
	private String video;


}
