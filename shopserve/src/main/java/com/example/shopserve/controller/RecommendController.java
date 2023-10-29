package com.example.shopserve.controller;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Rating;
import com.example.shopserve.entity.User;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.MoviesService;
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
@RequestMapping("/recommend")
@CrossOrigin
public class RecommendController {
    @Autowired
    private MoviesService moviesService;
    @GetMapping("/{userId}")
    public Result<List<Movies>> getRecommendMovies(@PathVariable(name=" userId") int userId){
        try{
            List<Movies> list = moviesService.selectAllMovies();
            return Result.ok(list);
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }
}
