package com.example.shopserve.controller;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Stars;
import com.example.shopserve.entity.ViewRecord;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.ViewRecordService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("viewRecord")
@Api(tags = "电影观看记录相关业务的接口文档")
@CrossOrigin(origins="/*") //服务器端解决跨域问题
public class ViewRecordController {

    @Autowired
    private ViewRecordService viewRecordService;
    //新增id浏览记录，删除id浏览记录，查看id浏览记录，通过浏览记录电影id获取电影信息

    //1新增id浏览记录
    @GetMapping("/save")
    public Result<ViewRecord> saveViewRecord(@RequestBody ViewRecord viewRecord){
        viewRecordService.saveViewRecord(viewRecord);

        return Result.ok(viewRecord);

    }
    //2删除id浏览记录
    @GetMapping("/delete")
    public Result deleteViewRecord(@RequestBody ViewRecord viewRecord){
        viewRecordService.deleteViewRecord(viewRecord);

        return Result.ok("取消收藏成功");

    }
    //得到某一位用户最近浏览过的所有电影
    @GetMapping("/all/{userId}")
    public Result<List<Movies>> getMoviesSeen(@PathVariable(name="userId") int userId){
        System.out.println(userId+"useriduserid");
        List<Movies> list = viewRecordService.getMoviesSeen(userId);

        return Result.ok(list);

    }

}
