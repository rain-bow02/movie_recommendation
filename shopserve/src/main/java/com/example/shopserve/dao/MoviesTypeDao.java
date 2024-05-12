package com.example.shopserve.dao;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.MoviesType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Movies)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Mapper
public interface MoviesTypeDao {


    List<MoviesType> getMoviesTypePagination(int offset, int size);

    List<MoviesType> getAllTypes();

    int selectTypesLength();

    MoviesType getMoviesTypeByName(String name);

    void insertType(MoviesType moviesType);

    MoviesType getMoviesTypeById(int id);

    void updateType(MoviesType moviesType);

    void deleteType(int id);

    void insertType_movie(int movieId, int typeId);

    int selectRelation(int movieId, int typeId);

    int deleteRelation(int movieId);
}

