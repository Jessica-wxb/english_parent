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
 * PhoneticWrong实体
 * 音标错题
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@ApiModel(value = "PhoneticWrongEntity:音标错题")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_phonetic_wrong")
public class PhoneticWrongEntity extends BaseEntity implements Serializable {
    public PhoneticWrongEntity(String id, String userId, String phonetic, Integer isStore, Integer falseType) {
        this.id = id;
        this.userId = userId;
        this.phonetic = phonetic;
        this.isStore = isStore;
        this.falseType = falseType;
    }

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private String userId;

    /**
     * 音标
     */
    @ApiModelProperty(value = "音标")
    @Column(name = "phonetic")
    private String phonetic;

    /**
     * 是否归仓（0表示未归仓  1表示已归仓）
     */
    @ApiModelProperty(value = "是否归仓（0表示未归仓  1表示已归仓）")
    @Column(name = "is_store")
    private Integer isStore;

    /**
     * 来源（0代表单词选音标 1代表音标选单词）
     */
    @ApiModelProperty(value = "来源（0代表单词选音标 1代表音标选单词）")
    @Column(name = "false_type")
    private Integer falseType;


}
