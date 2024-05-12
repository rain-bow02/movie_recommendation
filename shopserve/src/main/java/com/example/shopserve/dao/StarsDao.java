package com.example.shopserve.dao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Rating;
import com.example.shopserve.entity.Stars;
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
public interface StarsDao {

    /**
     * 根据用户名查找收藏的电影
     *
     *
     * @return 电影列表
     */
    List<Movies> getStarredMovies(int user_id,int offset, int size);

    void saveStars(Stars stars);

    void deleteStars(Stars stars);

    void deleteMovies(int movie_id);

    Stars hasStars(Stars stars);

    int selectStarredMoviesLength(int userId);
}
