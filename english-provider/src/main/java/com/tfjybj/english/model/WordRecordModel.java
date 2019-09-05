package com.tfjybj.english.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * WordRecordModel
 * 用户学习记录
 *
 * @author 张凯超 
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "WordRecordModel:用户学习记录")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class WordRecordModel implements Serializable {

    //region 模板逆向生产来自WordRecordEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "wordRecord主键")
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
	 * 是否检测：未检测：0；错误:1  正确2
	 */
    @ApiModelProperty(value = "是否检测：未检测：0；错误:1  正确2"  ,example="0" )
	private Integer status;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
