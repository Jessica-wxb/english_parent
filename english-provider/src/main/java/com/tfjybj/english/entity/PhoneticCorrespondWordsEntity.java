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
 * PhoneticCorrespondWords实体
 * 音标对应的单词表
 *
 * @author 马莹
 * @version 1.0.0
 * @since 2019-06-14 16:48:08
 */
@ApiModel(value = "PhoneticCorrespondWordsEntity:音标对应的单词表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonetic_correspond_words")
public class PhoneticCorrespondWordsEntity extends BaseEntity implements Serializable {

    /**
     * 单词全拼
     */
    @ApiModelProperty(value = "单词全拼")
    @Column(name = "word")
    private String word;

    /**
     * 音标id
     */
    @ApiModelProperty(value = "音标id")
    @Column(name = "phonefic_id")
    private String phoneficId;

    /**
     * 音频地址
     */
    @ApiModelProperty(value = "音频地址")
    @Column(name = "audio")
    private String audio;

    /**
     * y:元音,f:辅音
     */
    @ApiModelProperty(value = "y:元音,f:辅音")
    @Column(name = "vowel_or_consonant")
    private String vowelOrConsonant;


}
