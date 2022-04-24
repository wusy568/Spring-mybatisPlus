package com.wu.mybatisplus.controller.mongo;

import com.wu.mybatisplus.entity.MongoUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: MongoUserController
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-04-20
 **/
@Api(tags = "MongoDB CRUD")
@RestController
@RequestMapping("/mongoDB/controller")
public class MongoUserController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @return
     * @Author sy
     * @Description //TODO 插入文档
     * @Date 下午4:21 2022/4/22
     * @Param
     **/
    @ApiOperation(value = "插入一张文档", httpMethod = "POST")
    @PostMapping("/insertOne")
    public void insert() throws Exception {

        MongoUser mongoUser = new MongoUser();

        /*mongoUser.setId("10");*/
        mongoUser.setName("jerry");
        mongoUser.setAge(20);
        mongoUser.setAddress("天府大道");
        mongoUser.setCreatTime(new Date());

        mongoTemplate.insert(mongoUser);
    }

    @ApiOperation(value = "插入自定义文档", httpMethod = "POST")
    @PostMapping("/insert")
    public ResponseEntity<String> insertOne(@RequestBody MongoUser mongoUser) {

        /*mongoUser.setId("10");*/
        mongoUser.setName("jerry");
        mongoUser.setAge(20);
        mongoUser.setAddress("天府大道");
        mongoUser.setCreatTime(new Date());

        mongoTemplate.insert(mongoUser, "news");

        return ResponseEntity.ok("success");

    }

    @ApiOperation(value = "插入文档合集", httpMethod = "POST")
    @PostMapping("/insertList")
    public ResponseEntity<String> insertBatch(@RequestBody MongoUser mongoUser1) {

        List<MongoUser> mongoUserArrayList = new ArrayList<>();

        /*mongoUser.setId("10");*/
        mongoUser1.setName("jerry");
        mongoUser1.setAge(20);
        mongoUser1.setAddress("天府大道");
        mongoUser1.setCreatTime(new Date());
        mongoUserArrayList.add(mongoUser1);

        MongoUser mongoUser2 = new MongoUser();

        /*mongoUser.setId("10");*/
        mongoUser2.setName("jerry");
        mongoUser2.setAge(20);
        mongoUser2.setAddress("天府大道");
        mongoUser2.setCreatTime(new Date());
        mongoUserArrayList.add(mongoUser2);

        mongoTemplate.insert(mongoUserArrayList);

        return ResponseEntity.ok("success");

    }

    @ApiOperation(value = "save or update", httpMethod = "POST")
    @PostMapping("/update")
    public ResponseEntity<String> updateOne(@RequestBody MongoUser mongoUser) {

        mongoUser.setId("jack");
        mongoUser.setAge(20);
        mongoUser.setAddress("天府大道");
        mongoUser.setCreatTime(new Date());

        mongoTemplate.save(mongoUser);

        return ResponseEntity.ok("success");
    }


}
