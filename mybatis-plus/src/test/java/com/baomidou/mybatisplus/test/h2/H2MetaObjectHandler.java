package com.baomidou.mybatisplus.test.h2;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;
import java.util.Date;


/**
 * 测试，自定义元对象字段填充控制器，实现公共字段自动写入
 *
 * @author hubin
 * @since 2017-06-25
 */
public class H2MetaObjectHandler implements MetaObjectHandler {

    /**
     * 测试 user 表 name 字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // System.out.println("*************************");
        // System.out.println("insert fill");
        // System.out.println("*************************");

        // 测试下划线
        Object testType = this.getFieldValByName("testType", metaObject);
        // System.out.println("testType=" + testType);
        if (testType == null) {
            //测试实体没有的字段，配置在公共填充，不应该set到实体里面
            this.strictInsertFill(metaObject, "testType1", Integer.class, 3);
            this.strictInsertFill(metaObject, "testType", Integer.class, 3);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // System.out.println("*************************");
        // System.out.println("update fill");
        // System.out.println("*************************");
        //测试实体没有的字段，配置在公共填充，不应该set到实体里面
        this.strictUpdateFill(metaObject, "lastUpdatedDt1", Date.class, new Timestamp(System.currentTimeMillis()));
        this.strictUpdateFill(metaObject, "lastUpdatedDt", Date.class, new Timestamp(System.currentTimeMillis()));
    }
}

