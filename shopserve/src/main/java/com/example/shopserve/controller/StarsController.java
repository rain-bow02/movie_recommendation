package com.example.shopserve.controller;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Stars;
import com.example.shopserve.result.Pagination;
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
    @PostMapping("/saveStars")
    public Result<Stars> saveStars(@RequestBody Stars stars){
        Stars stars1=starsService.hasStars(stars);
        if(stars1!=null){
            return Result.error(500,"收藏失败，已收藏该电影");
        }else{
            starsService.saveStars(stars);
            Stars stars2=starsService.hasStars(stars);
            return Result.ok(stars2, "收藏成功");
        }
    }
    //4查找是否已收藏
    //1新增id收藏
    @PostMapping("/saved")
    public Result<Boolean> saved(@RequestBody Stars stars){
        Stars stars1=starsService.hasStars(stars);
        if(stars1!=null){
            return Result.ok(true,"已收藏该电影");
        }else{
            return Result.ok(false, "未收藏");
        }
    }
    //2删除id收藏
    @PostMapping("/delete")
    public Result deleteStars(@RequestBody Stars stars){
        starsService.deleteStars(stars);

        return Result.ok("取消收藏成功", "修改成功");

    }
    //3得到某一位用户收藏过的所有电影
    @GetMapping("/showStars/{userId}")
    public Result<Pagination<Movies>> getStarredMovies(@PathVariable(name="userId") int userId,int page){
        System.out.println(userId+"useriduserid");
        List<Movies> list = starsService.getStarredMovies(userId,page);
        int length=starsService.selectStarredMoviesLength(userId);
        Pagination pagi=Pagination.ok(list,length,page,20);
        return Result.ok(pagi);

//        return Result.ok(list, "返回该用户所有电影");

    }

}
