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
 * WordRecord实体
 * 用户学习记录
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "WordRecordEntity:用户学习记录")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_word_record")
public class WordRecordEntity extends BaseEntity implements Serializable {

	public WordRecordEntity(String wordId,String userId,Integer status){
		this.wordId=wordId;
		this.userId=userId;
		this.status=status;
	}

	public WordRecordEntity(String id,String userId,String wordId){
		this.id=id;
		this.userId=userId;
		this.wordId=wordId;
	}

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
	 * 是否检测：未检测：0；错误:1  正确2
	 */
    @ApiModelProperty(value = "是否检测：未检测：0；错误:1  正确2")
	@Column(name = "status")
	private Integer status;



}
