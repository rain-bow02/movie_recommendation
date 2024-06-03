package com.example.shopserve.dao;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Rating;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Movies)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Mapper
public interface RatingDao {
    int SaveRating(Rating rating);
    List<Movies> showRatingByUserId(int userId);

    int selectStarredMoviesLength(int userId);
}
