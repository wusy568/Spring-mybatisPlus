package com.wu.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @className: User
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-03-23
 **/
@ApiModel(value = "userInformation")
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String password;

    private String firstName;

    private String lastName;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteStatus;

}
