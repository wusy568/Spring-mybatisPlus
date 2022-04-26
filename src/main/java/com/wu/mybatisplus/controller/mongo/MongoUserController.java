package com.wu.mybatisplus.controller.mongo;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wu.mybatisplus.entity.MongoUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<String> saveOrUpdate(@RequestBody MongoUser mongoUser) {

        mongoUser.setName("jack");
        mongoUser.setAge(20);
        mongoUser.setAddress("天府大道");
        mongoUser.setCreatTime(new Date());

        mongoTemplate.save(mongoUser);

        return ResponseEntity.ok("success");
    }

    //更新文档
    @ApiOperation(value = "update", httpMethod = "POST")
    @PostMapping("/update")
    public ResponseEntity<String> updateOne(@RequestBody MongoUser mongoUser) {

        mongoUser.setName("jack");
        mongoUser.setAge(21);
        mongoUser.setAddress("天府大道东");
        mongoUser.setCreatTime(new Date());

        //更新条件
        Query query = new Query(Criteria.where("id").is(mongoUser.getId()));

        //更新值
        Update update = new Update().set("Name", mongoUser.getName()).set("Age",mongoUser.getAge()).set("Address",mongoUser.getAddress());

        UpdateResult updateResult = mongoTemplate.updateFirst(query,update,MongoUser.class);

        return ResponseEntity.ok("success");
    }

    //删除文档
    @ApiOperation(value = "deleteOne", httpMethod = "POST")
    @PostMapping("/deleteOne")
    public ResponseEntity<String> deleteOne() {

        MongoUser mongoUser = mongoTemplate.findById(new ObjectId("626105a7768dfa8cd3ef04f5"),MongoUser.class);

        //更新条件
        Query query = new Query(Criteria.where("id").is(mongoUser.getId()));

        DeleteResult deleteResult = mongoTemplate.remove(query,MongoUser.class);

        System.out.println("被删除的文档内容:" + deleteResult.toString());
        System.out.println("被删除的条数" + deleteResult.getDeletedCount());

        return ResponseEntity.ok("success");
    }

    //查询集合中的所有文档
    @ApiOperation(value = "findAll", httpMethod = "POST")
    @PostMapping("/findAll")
    public ResponseEntity<String> findAll() {

       List<MongoUser> list = mongoTemplate.findAll(MongoUser.class);

        return ResponseEntity.ok("查询结果：" + list.toString());
    }

    //查询集合中的指定文档
    @ApiOperation(value = "findOne", httpMethod = "POST")
    @PostMapping("/findOne")
    public ResponseEntity<String> findOne() {

        MongoUser mongoUser = mongoTemplate.findById(new ObjectId("62610611768dfa8cd3ef04f7"),MongoUser.class);

        return ResponseEntity.ok("查询结果：" + mongoUser.toString());
    }

    //模糊查询
    @ApiOperation(value = "模糊查询", httpMethod = "POST")
    @PostMapping("/findLikesName")
    public ResponseEntity<String> findLikesName() {

        String name = "je";

        String regex = String.format("%s%s%s", "^.*", name, ".*$");

        Pattern compile = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Query name1 = new Query(Criteria.where("name").regex(compile));

        List<MongoUser> mongoUser = mongoTemplate.find(name1,MongoUser.class);

        return ResponseEntity.ok("查询结果：" + mongoUser.toString());
    }

    //条件查询
    @ApiOperation(value = "条件查询", httpMethod = "POST")
    @PostMapping("/conditionalQuery")
    public ResponseEntity<String> findPage() {

        String name = "jerry";

        //从第一行开始，查询两条数据返回
        Query query = new Query(Criteria.where("name").is(name)).with(Sort.by("age"));

        List<MongoUser> list = mongoTemplate.find(query,MongoUser.class);

        return ResponseEntity.ok("查询结果：" + list.toString());
    }

    //分页查询
    @ApiOperation(value = "分页查询", httpMethod = "POST")
    @PostMapping("/queryByPage")
    public ResponseEntity<String> queryByPage() {

        String name = "jerry";

        //从第一行开始，查询两条数据返回
        Query query = new Query(Criteria.where("name").is(name)).with(Sort.by("createTime")).limit(2).skip(2);

        List<MongoUser> list = mongoTemplate.find(query,MongoUser.class);

        return ResponseEntity.ok("查询结果：" + list.toString());
    }
}
