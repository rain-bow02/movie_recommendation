package com.example.shopserve.controller;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.MoviesType;
import com.example.shopserve.result.Pagination;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.MoviesService;
import com.example.shopserve.service.MoviesTypeService;
import com.example.shopserve.service.StarsService;
import com.example.shopserve.service.ViewRecordService;
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
    @Autowired
    private MoviesTypeService moviesTypeService;
    @Autowired
    private StarsService starsService;
    @Autowired
    private ViewRecordService viewRecordService;

    /*查找所有电影*/
    @Operation(summary = "展示所有电影",
            responses = {
                    @ApiResponse(description = "返回：所有电影"),
            }
    )
    @GetMapping("/getAllMovies")
    public Result<Pagination<Movies>> getAllMovies(int page){
        try{
            List<Movies> list = moviesService.selectMoviesPage(page);
            int length=moviesService.selectMoviesLength();
            Pagination pagi=Pagination.ok(list,length,page,20);
            return Result.ok(pagi);
//            return Result.ok(list);
        }catch (Exception e){
            return Result.error(500,"服务器繁忙，请稍后重试");
        }
    }
    /*根据电影类型查找电影*/
    @Operation(summary = "展示电影类型的所有电影",
            responses = {
                    @ApiResponse(description = "返回：当前类型的所有电影"),
            }
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "电影类型id",  required = true, dataTypeClass = java.lang.String.class)
    })
    @GetMapping("/inTypeMovies/{typeId}")
    public Result<Pagination<Movies>> getInTypeMovies(@PathVariable(name="typeId") int typeId,int page){

        System.out.println(typeId+"getInTypeMovies"+page);
//        try{
            List<Movies> list = moviesService.showInTypeMovies(typeId,page);
            int length=moviesService.selectInTypeMoviesLength(typeId);
            Pagination pagi=Pagination.ok(list,length,page,20);
            return Result.ok(pagi);
//            return Result.ok(list);
//        }catch (Exception e){
//
//            return Result.error(500,"服务器繁忙，请稍后重试");
//        }
    }
    //得到某一位用户的推荐电影
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
    public Result<Pagination<Movies>> searchMovies(@PathVariable(name="selectCondition") String selectCondition,int page){
        try{
        List<Movies> list = moviesService.searchMovies(selectCondition,page);
        int length=moviesService.searchMoviesLength(selectCondition);
        Pagination pagi=Pagination.ok(list,length,page,20);
        return Result.ok(pagi);
        }catch (Exception e){

            return Result.error(500,"没有符合条件的电影");
        }
    }

    /**
     * 新增电影，新增了电影还要新增电影_电影类型，如果没有类型还要新增类型
     *
     * @param movies
     * @return 实例对象
     */
    @PostMapping("/insert")
    public Result<String> insertMovie(@RequestBody Movies movies)  {
        List<Movies> moviesList= this.moviesService.searchMoviesByAllName(movies.getName());
        if(moviesList.size()>0){
            return Result.error(500,"新增失败，电影名称已存在");
        }
        else{//电影
            System.out.println("新增电影" );
            this.moviesService.insertMovie(movies);
            Movies movies1= this.moviesService.searchMoviesByAllName(movies.getName()).get(0);
            //类别
            this.moviesTypeService.insertMovieRelation(movies1);
            return Result.ok("新增成功");
        }

    }

    /**
     * 修改电影，有修改电影类型还要新增电影_电影类型，如果没有类型还要新增类型
     *
     * @param movies
     * @return 实例对象
     */
    @PostMapping("/update/{id}")
    public Result<Movies> updateMovie(@RequestBody Movies movies, @PathVariable int id)  {
        Movies movie= this.moviesService.queryById(id);
        if(movie == null){
            return Result.error(500,"修改失败，电影不存在");
        }
        else{
            this.moviesService.updateMovie(movies,id);
            Movies movie1= this.moviesService.queryById(id);
            //类别
            if( movies.getGenre()!=null){
                if(movies.getId()==null) movies.setId(id);
                this.moviesTypeService.insertMovieRelation(movies);
            }

            return Result.ok(movie1,"修改成功");
        }

    }

    /**
     * 删除电影,删除电影_电影类型
     *
     * @param id
     * @return 实例对象
     */
    @PostMapping("/delete/{id}")
    public Result<String> DeleteMovie( @PathVariable int id)  {
        Movies movie= this.moviesService.queryById(id);
        if(movie == null){
            return Result.error(500,"删除失败，电影不存在");
        }
        else{
            //删除
            this.moviesTypeService.deleteRelation(id);
            this.starsService.deleteMovies(id);
            this.viewRecordService.deleteMovies(id);
            this.moviesService.deleteMovie(id);

            return Result.ok("删除成功");
        }
    }

}
