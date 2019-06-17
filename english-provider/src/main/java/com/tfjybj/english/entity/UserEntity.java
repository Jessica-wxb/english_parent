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
 * User实体
 * 用户
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@ApiModel(value = "UserEntity:用户")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tn_user")
public class UserEntity extends BaseEntity implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户code
     */
    @ApiModelProperty(value = "用户code", required = true)
    @Column(name = "user_code")
    private String userCode;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @Column(name = "user_name")
    private String userName;


}
