package com.example.shopserve.controller;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.MoviesService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("movies")
@Api(tags = "电影相关业务的接口文档")
@CrossOrigin(origins="/*") //服务器端解决跨域问题
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    /*查找所有电影*/
    @Operation(summary = "展示所有电影",
            responses = {
                    @ApiResponse(description = "返回：所有电影"),
            }
    )
    @GetMapping("/getAllMovies")
    public Result<List<Movies>> getAllMovies(){
        try{
            List<Movies> list = moviesService.selectAllMovies();
            return Result.ok(list);
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }


    @Operation(summary = "展示电影类型的所有电影",
            responses = {
                    @ApiResponse(description = "返回：当前类型的所有电影"),
            }
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "电影类型id",  required = true, dataTypeClass = java.lang.String.class)
    })
    @GetMapping("/inTypeMovies/{typeId}")
    public Result<List<Movies>> getInTypeMovies(@PathVariable(name="typeId") String typeId){

        try{
            System.out.println("sdsda");
            List<Movies> list = moviesService.showInTypeMovies(typeId);
            System.out.println("sdsda");
            return Result.ok(list);
        }catch (Exception e){

            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }
    //得到某一位用户评价过的所有电影
    @GetMapping("/recommend/{userId}")
    public Result<List<Movies>> getRecommendMovies(@PathVariable(name="userId") int userId){
        long startTime = System.nanoTime();
            System.out.println(userId+"useriduserid");
            List<Movies> list = moviesService.getRecommendMovies(userId);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  // 计算运行时间

        System.out.println("运行时间：" + duration);

        return Result.ok(list);

    }
    /**
     * 根据电影名字查询电影
     *
     * @param selectCondition 查询条件
     * @return 电影列表
     */
    @GetMapping("/search/{selectCondition}")
    public Result<List<Movies>> searchMovies(@PathVariable(name="selectCondition") String selectCondition){
        return Result.ok(this.moviesService.searchMovies(selectCondition));
    }

    /**
     * 新增电影
     *
     * @param id 主键
     * @return 实例对象
     */


}
