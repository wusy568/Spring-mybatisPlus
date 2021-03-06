package com.wu.mybatisplus.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @className: MyMetaObjectHandler
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-03-23
 **/
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("start insert fill");

        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        //设置逻辑删除默认值
        this.setFieldValByName("deleteStatus",0,metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("start update fill");

        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

    }


}
