package com.wu.mybatisplus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Status;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;


/**
 * @className: MongoUser
 * @description: TODO 类描述,使用 Lombok 中的 @Accessors(chain = true) 注解，能让我们方便使用链式方法创建实体对象
 * @author: sy
 * @date: 2022/4/22
 **/
@Data
@ToString
@Accessors(chain = true)
@Document(collection = "example_data_1")
public class MongoUser {

    @MongoId
    private String id;

    private String name;

    private Integer age;

    private String address;

    private Date creatTime;
}
