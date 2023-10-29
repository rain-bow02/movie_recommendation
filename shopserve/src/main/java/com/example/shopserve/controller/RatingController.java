package com.example.shopserve.controller;


import com.example.shopserve.entity.Rating;
import com.example.shopserve.entity.User;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.RatingService;
import com.example.shopserve.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rating")
@CrossOrigin
public class RatingController {
    @Autowired
    private RatingService ratingService;
    //保存电影评论
    @PostMapping("/saveRating")
    public Result<Rating> SaveRating(@RequestBody Rating rating) throws SQLException, ClassNotFoundException {
        System.out.println(rating.getScore());
//        try {
            ratingService.SaveRating(rating);
//            sqlSession.commit();
//            sqlSession.rollback();
//            sqlSession.close();
            System.out.println("RatingControllerokok");
            return Result.ok(rating);
//        }
//        catch (Exception e){
//            return Result.error(404,"登录失败！");
//        }
    }
    //查询某个用户的评论
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id",  required = true, dataTypeClass = java.lang.String.class)
    })
    @GetMapping("/showRating/{userId}")
    public Result<List<Rating>> showRatingByUserId(@PathVariable(name="userId") int userId) {

            System.out.println(userId+"userid");
            List<Rating> list = ratingService.showRatingByUserId(userId);
            System.out.println(list);
            return Result.ok(list);
    }

//    public Map<Integer, Map<Integer,Integer>> RshowRatingByMovieId(@PathVariable(name="userId") int userId) {
//        System.out.println(userId+"userid");
//        List<Rating> list = ratingService.showRatingByUserId(userId);
//        System.out.println(list);
//        return list;
//    }

}




