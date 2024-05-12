package com.example.shopserve.service;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.MoviesType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface MoviesTypeService {
    int selectTypesLength();

    List<MoviesType> getMoviesTypeByPage(int page);

    List<MoviesType> getMoviesTypeByPageSize(int page, int size);

    MoviesType getMoviesTypeByName(String name);

    MoviesType insertType(MoviesType moviesType);

    MoviesType getMoviesTypeById(int id);

    MoviesType updateType(MoviesType moviesType);

    void deleteType(int id);

    void insertType_movie(int movieId, int typeId);
    int deleteRelation(int movieId);
    void insertMovieRelation(Movies movies1);

//    Result<Pagination<Movies>> getMoviesByPage(int page, int size);
}
