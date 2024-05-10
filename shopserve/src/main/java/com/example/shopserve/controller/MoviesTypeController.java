package com.example.shopserve.controller;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.MoviesType;
import com.example.shopserve.result.Pagination;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.MoviesService;
import com.example.shopserve.service.MoviesTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("moviesType")
@Api(tags = "电影类型相关业务的接口文档")
@CrossOrigin(origins="/*") //服务器端解决跨域问题
public class MoviesTypeController {
    //1、新增，修改，删除、分页查询
    @Autowired
    private MoviesTypeService moviesTypeService;

    /*分页查询电影类型*/
    @Operation(summary = "分页查询电影类型",
            responses = {
                    @ApiResponse(description = "返回：分页查询电影类型"),
            }
    )
    @GetMapping ("/getAllTypes")
    public Result<Pagination<MoviesType>> getAllTypes(int page, int size){
        try{
            List<MoviesType> data=moviesTypeService.getMoviesTypeByPageSize(page, size);
            Pagination pagi=Pagination.ok(data,page,size);
//            Result<Pagination<Movies>> movies=moviesTypeService.getMoviesByPage(page, size);
//            List<Movies> list = moviesTypeService.selectAllTypes();
            return Result.ok(pagi);
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }
    @GetMapping("/getAllTypesPage")
    public Result<Pagination<MoviesType>> getAllTypes(int page){
        try{
            List<MoviesType> data=moviesTypeService.getMoviesTypeByPage(page);
            Pagination pagi=Pagination.ok(data,page);
            return Result.ok(pagi);
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }
}
