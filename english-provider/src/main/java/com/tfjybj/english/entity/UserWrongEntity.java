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
 * UserWrong实体
 * 错题本
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "UserWrongEntity:错题本")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_user_wrong")
public class UserWrongEntity extends BaseEntity implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    @Column(name = "user_id")
    private String userId;

    /**
     * 音标id
     */
    @ApiModelProperty(value = "音标id", required = true)
    @Column(name = "phonefic_id")
    private String phoneficId;

    /**
     * 错误音标
     */
    @ApiModelProperty(value = "错误音标")
    @Column(name = "phonefic")
    private String phonefic;

    /**
     * 单词id
     */
    @ApiModelProperty(value = "单词id", required = true)
    @Column(name = "word_id")
    private String wordId;

    /**
     * 错误单词
     */
    @ApiModelProperty(value = "错误单词")
    @Column(name = "word")
    private String word;

    /**
     * 状态 ：1，单词  ；2，音标
     */
    @ApiModelProperty(value = "状态 ：1，单词  ；2，音标", required = true)
    @Column(name = "type")
    private Integer type;

    /**
     * 错误图片
     */
    @ApiModelProperty(value = "错误图片")
    @Column(name = "picture1")
    private String picture1;

    /**
     * 错误图片
     */
    @ApiModelProperty(value = "错误图片")
    @Column(name = "picture2")
    private String picture2;

    /**
     * 错误图片
     */
    @ApiModelProperty(value = "错误图片")
    @Column(name = "picture3")
    private String picture3;

    /**
     * 错误图片
     */
    @ApiModelProperty(value = "错误图片")
    @Column(name = "picture4")
    private String picture4;

    /**
     * 错误音标类型
     */
    @ApiModelProperty(value = "错误音标类型")
    @Column(name = "phoneficType")
    private String phoneficType;


}
