package com.tfjybj.english.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dmsdbj.itoo.tool.tenancy.TenancyContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 共有字段的填充
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2019-1-24 11:36:03
 */
@Configuration
public class ToolMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createFieldType = getFieldValByName("createTime", metaObject);
        Object updateFieldType = getFieldValByName("updateTime", metaObject);
        Object operator = getFieldValByName("operator", metaObject);

        if (createFieldType == null) {
            setFieldValByName("createTime", new Date(), metaObject);
        }
        if (updateFieldType == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        if (operator == null) {
            setFieldValByName("operator", TenancyContext.UserID.get(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateFieldType = getFieldValByName("updateTime", metaObject);
        Object operator = getFieldValByName("operator", metaObject);

        if (updateFieldType == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        if (operator == null) {
            setFieldValByName("operator", TenancyContext.UserID.get(), metaObject);
        }
    }

}
