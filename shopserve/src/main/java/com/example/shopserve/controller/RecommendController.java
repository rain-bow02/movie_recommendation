package com.example.shopserve.controller;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return Result.ok(list, "修改成功");
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }
}
