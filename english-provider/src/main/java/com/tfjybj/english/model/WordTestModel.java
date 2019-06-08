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
 * WordTestModel
 * 单词练习
 *
 * @author 马莹 
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "WordTestModel:单词练习")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class WordTestModel implements Serializable {

    //region 模板逆向生产来自WordTestEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "wordTest主键")
	@Column(name = "id")
	private String id;
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
	 * 状态（音标正确与否）
	 */
	@ApiModelProperty(value = "状态（音标正确与否）",required = true  ,example="0" )
	private Integer state;

	/**
	 * 创建时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "创建时间",required = true )
	private Date creatTime;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
