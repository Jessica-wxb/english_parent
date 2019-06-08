package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PhoneficModel
 * 音标
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "PhoneficModel:音标")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneficModel implements Serializable {

    //region 模板逆向生产来自PhoneficEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "phonefic主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 音标
	 */
	@ApiModelProperty(value = "音标",required = true )
	private String phonefic;

	/**
	 * 音标发音图片-地址
	 */
	@ApiModelProperty(value = "音标发音图片-地址",required = true )
	private String phoneficPicture;

	/**
	 * 音标诀窍图片-地址
	 */
	@ApiModelProperty(value = "音标诀窍图片-地址",required = true )
	private String knackPicture;

	/**
	 * 音频
	 */
	@ApiModelProperty(value = "音频",required = true )
	private String audio;

	/**
	 * 视频
	 */
	@ApiModelProperty(value = "视频",required = true )
	private String video;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
