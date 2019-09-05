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
 * NewPicture实体
 * 用户新图表
 *
 * @author 张凯超 
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "NewPictureEntity:用户新图表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_new_picture")
public class NewPictureEntity extends BaseEntity implements Serializable {

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true)
	@Column(name = "user_id")
	private String userId;

	/**
	 * 单词id
	 */
    @ApiModelProperty(value = "单词id")
	@Column(name = "word_id")
	private String wordId;

	/**
	 * 图片id
	 */
    @ApiModelProperty(value = "图片id")
	@Column(name = "picture_address")
	private String pictureAddress;


}
