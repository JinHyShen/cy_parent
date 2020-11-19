package com.hong.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充字段配置
 */
@Component
public class EduMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName ( "gmtCreate",new Date (  ),metaObject );
        this.setFieldValByName ( "gmtModified",new Date (  ),metaObject );
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName ( "gmtModified",new Date (  ),metaObject );
    }


}
