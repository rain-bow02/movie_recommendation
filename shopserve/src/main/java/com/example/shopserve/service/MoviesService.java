package com.example.shopserve.service;

import com.example.shopserve.entity.Movies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * (Movies)表服务接口
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Service
@Transactional
public interface MoviesService {

    List<Movies> getRecommendMovies(int userId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Movies queryById(Integer id);


    /**
     * 展示属于该菜品的菜肴
     * @param typeId
     * @param page
     * @return
     */
    List<Movies> showInTypeMovies(int typeId, int page);



    List<Movies> selectAllMovies();

    List<Movies> searchMovies(String selectCondition, int page);

    List<Movies> searchMoviesByAllName(String selectCondition);

    void insertMovie(Movies movies);

    void updateMovie(Movies movies,int id);

    void deleteMovie(int id);

    int selectMoviesLength();

    List<Movies> selectMoviesPage(int page);

    int selectInTypeMoviesLength(int typeId);

    int searchMoviesLength(String selectCondition);

    List<Movies> getSimilar(int id);
//
//    List<Movies> getStarredMovies(int userId);
//
//    List<Movies> getMoviesSeen(int userId);
}
