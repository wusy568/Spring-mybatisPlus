package com.wu.mybatisplus.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @className: Member
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-04-08
 **/
@Data
@EqualsAndHashCode
public class Member {

    @Excel(name = "ID",width = 10)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Date birthday;
    private String phone;
    private String icon;
    private Integer gender;
}
