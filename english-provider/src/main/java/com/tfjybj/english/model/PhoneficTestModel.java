package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * PhoneficTestModel
 * 音标练习
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "PhoneficTestModel:音标练习")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PhoneficTestModel implements Serializable {

    //region 模板逆向生产来自PhoneficTestEntity的属性

	/**
	 * word表id_邢美玲
	 */
	@ApiModelProperty(value = "音频audio")
	@Column(name = "audio")
	private String audio;
	/**
	 * id
	 */
	@ApiModelProperty(value = "phoneficTest主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 音标id
	 */
	@ApiModelProperty(value = "音标id",required = true )
	private String phoneficId;

	/**
	 * 音标
	 */
    @ApiModelProperty(value = "音标" )
	private String phonefic;

	/**
	 * 单词id
	 */
	@ApiModelProperty(value = "单词id",required = true )
	private String wordId;

	/**
	 * 单词
	 */
    @ApiModelProperty(value = "单词" )
	private String word;

	/**
	 * 状态（单词正确与否）
	 */
	@ApiModelProperty(value = "状态（单词正确与否）",required = true  ,example="0" )
	private Integer state;

	/**
	 * 更新时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "更新时间",required = true )
	private Date updteTime;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
