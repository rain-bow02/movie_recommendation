package com.example.shopserve;

import com.example.shopserve.controller.StarsController;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.MoviesService;
import com.example.shopserve.service.impl.MoviesServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ShopserveApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime now2 = LocalDateTime.now(); // 获取当前日期和时间
        System.out.println(now2);
//        MoviesService starsController = new StarsController();
//        starsController.getStarredMovies(1);
////        List<Movies> movieList = new ArrayList<>();
////        Result<List<Movies>> movieResult = new Result<>(movieList);
//        System.out.println("sdfsfd");
//        System.out.println(starsController.getStarredMovies(1));


    }

}
