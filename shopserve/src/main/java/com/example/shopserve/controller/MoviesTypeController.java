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

    /*分容量分页查询电影类型*/
    @Operation(summary = "分页查询电影类型",
            responses = {
                    @ApiResponse(description = "返回：分页查询电影类型"),
            }
    )
    @GetMapping ("/getAllTypes")
    public Result<Pagination<MoviesType>> getAllTypes(int page, int size){
        try{
            List<MoviesType> data=moviesTypeService.getMoviesTypeByPageSize(page, size);
            int length=moviesTypeService.selectTypesLength();
            Pagination pagi=Pagination.ok(data,length,page,size);
//            Result<Pagination<Movies>> movies=moviesTypeService.getMoviesByPage(page, size);
//            List<Movies> list = moviesTypeService.selectAllTypes();
            return Result.ok(pagi);
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }

    /*分页查询电影类型*/
    @GetMapping("/getAllTypesPage")
    public Result<Pagination<MoviesType>> getAllTypes(int page){
        try{
            List<MoviesType> data=moviesTypeService.getMoviesTypeByPage(page);
            int length=moviesTypeService.selectTypesLength();
            Pagination pagi=Pagination.ok(data,length,page);
            return Result.ok(pagi);
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }
    /*新增电影类型*/
    @Operation(summary = "新增电影类型",
            responses = {
                    @ApiResponse(description = "返回：新增的电影类型"),
            }
    )
    @PostMapping ("/insert")
    public Result<MoviesType> insertType(@RequestBody MoviesType moviesType ){
        try{
            MoviesType data=moviesTypeService.getMoviesTypeByName(moviesType.getName());
            if(data==null){
                moviesTypeService.insertType(moviesType);
                MoviesType type=moviesTypeService.getMoviesTypeByName(moviesType.getName());

                return Result.ok(type,"新增电影类型成功");
            }
            else{
                return Result.error(500,"新增失败，电影类型已存在");
            }
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }

    @PostMapping ("/update/{id}")
    public Result<MoviesType> updateType(@RequestBody MoviesType moviesType, @PathVariable int id ){
        try{
            //修改的电影存在且新修改的名字不存在
            MoviesType data=moviesTypeService.getMoviesTypeById(id);
            MoviesType name=moviesTypeService.getMoviesTypeByName(moviesType.getName());
            if(data!=null&&name==null){//datd1 name0  data0 name1 name0  data1 name1
                if(moviesType.getId()==null){
                    moviesType.setId(id);
                }
                moviesTypeService.updateType(moviesType);
                MoviesType type=moviesTypeService.getMoviesTypeByName(moviesType.getName());
                return Result.ok(type,"修改电影类型成功");
            }
            else if(data==null){
                return Result.error(500,"修改失败，电影类型不存在");
            }
            else if(moviesType.getName().equals(data.getName())){
                return Result.error(500,"修改失败，电影类型无变化");
            }
            else return Result.error(500,"修改失败，请稍后再试");
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }

    @PostMapping ("/delete/{id}")
    public Result<MoviesType> deleteType( @PathVariable int id ){
        try{
            MoviesType data=moviesTypeService.getMoviesTypeById(id);
            if(data!=null){
                moviesTypeService.deleteType(id);
                MoviesType type=moviesTypeService.getMoviesTypeById(id);
                return Result.ok(type,"删除电影类型成功");
            }
            else{
                return Result.error(500,"修改失败，电影类型不存在");
            }
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }
}
