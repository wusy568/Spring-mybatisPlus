package com.wu.mybatisplus.controller.mybatisPlus;

import com.wu.mybatisplus.entity.User;
import com.wu.mybatisplus.mapper.UserMapper;
import com.wu.mybatisplus.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @className: UserController
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-03-23
 **/
@Api(tags = "CRUD控制器")
@RestController
@RequestMapping("/controller")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    //插入一条数据
    @ApiOperation(value = "插入一条数据", httpMethod = "POST")
    @PostMapping("/insert")
    @ApiImplicitParam(name = "userParam", type = "body", dataTypeClass = User.class, required = true)
    public ResponseEntity<String> insert(@RequestBody User user) {

        return ResponseEntity.ok("success");

    }

    //批量插入数据（List）
    @ApiOperation(value = "批量插入数据",httpMethod = "POST")
    @PostMapping("/saveBatch")
    public boolean saveBatch() {

        List<User> list = new ArrayList<>();
        // 待添加（用户）数据
        for (int i = 0; i < 2000; i++) {
            User user = new User();
            user.setPassword("123456");
            user.setFirstName("test:" + i);
            user.setLastName("last" + i);
            user.setDeleteStatus(0);
            list.add(user);
        }

        //批量插入
        return userService.saveBatch(list);

    }

    //批量插入数据,有次数
    @ApiOperation(value = "批量插入数据，自定义数据",httpMethod = "POST")
    @PostMapping("/saveBatchByNums")
    public boolean saveBatchNums(@RequestBody Collection<User> collection) {

        for (int j = 0; j < 100; j++) {
            User user = new User();
            user.setPassword("123");
            user.setFirstName("first" + j);
            user.setLastName("last" + j);
            user.setDeleteStatus(0);
            collection.add(user);
        }

        //批量插入
        return userService.saveBatch(collection);

    }

    //没数据新增一条，有数据修改一条
    @ApiOperation(value = "新增或者修改",httpMethod = "PUT")
    @PutMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user) {

        return userService.saveOrUpdate(user);

    }

    //根据ID修改
    @ApiOperation(value = "根据ID修改数据",httpMethod = "POST")
    @PostMapping("/updateById/{userId}")
    public boolean updateById(User user) {
        user.setPassword("1234qwer");
        user.setFirstName("update");
        user.setLastName("update");
        user.setDeleteStatus(0);
        return userService.updateById(user);
    }


    //通过ID查询单条数据
    @ApiOperation(value = "通过ID查询数据",httpMethod = "GET")
    @GetMapping("/getUserInfo/{userId}")
    public User getUserInfo(@PathVariable("userId") Integer userId) {
        return userService.getById(userId);
    }

    //单条删除
    @ApiOperation(value = "逻辑删除，service方法",httpMethod = "DELETE")
    @DeleteMapping("/deleteOne/{userId}")
    public boolean deleteOne(@PathVariable("userId") Integer userId) {

        return userService.removeById(userId);

    }

    //逻辑删除
    @ApiOperation(value = "逻辑删除，mapper方法",httpMethod = "POST")
    @PostMapping("/deleteOne1/{userId}")
    public Integer deleteOne1(@PathVariable("userId") Integer userId) {

        return userMapper.deleteById(userId);

    }





}
