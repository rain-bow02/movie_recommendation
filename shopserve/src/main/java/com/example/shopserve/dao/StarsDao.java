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
    List<Movies> getStarredMovies(int userId);

    void saveStars(Stars stars);

    void deleteStars(Stars stars);
}
