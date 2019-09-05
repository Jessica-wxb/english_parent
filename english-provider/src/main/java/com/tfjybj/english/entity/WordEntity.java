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
 * Word实体
 * 单词表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@ApiModel(value = "WordEntity:单词表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_word")
public class WordEntity extends BaseEntity implements Serializable {

	/**
	 * 单词
	 */
    @ApiModelProperty(value = "单词")
	@Column(name = "word")
	private String word;

	/**
	 * 单词图片地址1
	 */
    @ApiModelProperty(value = "单词图片地址1")
	@Column(name = "word_picture1")
	private String wordPicture1;

	/**
	 * 单词图片地址2
	 */
    @ApiModelProperty(value = "单词图片地址2")
	@Column(name = "word_picture2")
	private String wordPicture2;

	/**
	 * 单词图片地址3
	 */
    @ApiModelProperty(value = "单词图片地址3")
	@Column(name = "word_picture3")
	private String wordPicture3;

	/**
	 * 单词图片地址4
	 */
    @ApiModelProperty(value = "单词图片地址4")
	@Column(name = "word_picture4")
	private String wordPicture4;

	/**
	 * 单词图片地址5
	 */
    @ApiModelProperty(value = "单词图片地址5")
	@Column(name = "word_picture5")
	private String wordPicture5;

	/**
	 * 音频
	 */
    @ApiModelProperty(value = "音频")
	@Column(name = "audio")
	private String audio;


}
