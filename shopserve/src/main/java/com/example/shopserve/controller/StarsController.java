package com.example.shopserve.controller;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Stars;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.StarsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("stars")
@Api(tags = "电影收藏相关业务的接口文档")
@CrossOrigin(origins="/*") //服务器端解决跨域问题
public class StarsController {

    @Autowired
    private StarsService starsService;
    //1新增id收藏，2删除id收藏，3查看id收藏

    //1新增id收藏
    @GetMapping("/save")
    public Result<Stars> saveStars(@RequestBody Stars stars){
         starsService.saveStars(stars);

        return Result.ok(stars, "修改成功");

    }
    //2删除id收藏
    @GetMapping("/delete")
    public Result deleteStars(@RequestBody Stars stars){
        starsService.deleteStars(stars);

        return Result.ok("取消收藏成功", "修改成功");

    }
    //3得到某一位用户收藏过的所有电影
    @GetMapping("/all/{userId}")
    public Result<List<Movies>> getStarredMovies(@PathVariable(name="userId") int userId){
        System.out.println(userId+"useriduserid");
        List<Movies> list = starsService.getStarredMovies(userId);

        return Result.ok(list, "修改成功");

    }

}
