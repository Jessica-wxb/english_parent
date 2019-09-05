package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * NewPictureModel
 * 用户新图表
 *
 * @author 张凯超 
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "NewPictureModel:用户新图表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class NewPictureModel implements Serializable {

    //region 模板逆向生产来自NewPictureEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "newPicture主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true )
	private String userId;

	/**
	 * 单词id
	 */
    @ApiModelProperty(value = "单词id" )
	private String wordId;

	/**
	 * 图片id
	 */
    @ApiModelProperty(value = "图片id" )
	private String pictureAddress;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
