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
 * WordWrong实体
 * 单词错题
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "WordWrongEntity:单词错题")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_word_wrong")
public class WordWrongEntity extends BaseEntity implements Serializable {

	public WordWrongEntity(String id){
		this.id=id;
	}

	public WordWrongEntity(String userId,String wordId,Integer isCheck){
		this.userId = userId;
		this.wordId = wordId;
		this.isCheck= isCheck;
	}

	public WordWrongEntity(String id ,String userId,String wordId){
		this.id = id;
		this.userId = userId;
		this.wordId = wordId;
	}

	public WordWrongEntity(String userId,String wordId){
		this.userId = userId;
		this.wordId = wordId;
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
	 * 是否学习 0，未学习  ；1，已学习
	 */
	@ApiModelProperty(value = "是否学习 0，未学习  ；1，已学习",required = true)
	@Column(name = "is_study")
	private Integer isStudy;

	/**
	 * 是否检测正确 0错误 ；1正确
	 */
	@ApiModelProperty(value = "是否检测正确 0错误 ；1正确",required = true)
	@Column(name = "is_check")
	private Integer isCheck;


}
