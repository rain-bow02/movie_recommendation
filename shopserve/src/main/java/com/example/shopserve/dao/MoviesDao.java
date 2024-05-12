package com.example.shopserve.dao;

import com.example.shopserve.entity.Movies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Movies)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Mapper
public interface MoviesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Movies queryById(Integer id);



    /**
     * 根据菜品类型id查询菜品
     * @param typeId
     * @return
     */
    List<Movies>  selectByType(int typeId,int offset, int size);


    /**
     * 查找菜品信息
     *
     *
     * @return 菜品列表
     */
    List<Movies> queryAllMovies();


    List<Movies> selectMoviesByCondition(String selectCondition,int offset, int size);
//    selectmoviesByAllName
    List<Movies> selectmoviesByAllName(String selectCondition);

    /**
     * 插入电影
     */
    void insertMovie(Movies movies);

    void updateMovie(Movies movies,int id);

    void deleteMovie(int id);

    int selectMoviesLength();

    List<Movies> selectMoviesPage(int offset, int size);

    int selectInTypeMoviesLength(int typeId);

    int searchMoviesLength(String selectCondition);
}

