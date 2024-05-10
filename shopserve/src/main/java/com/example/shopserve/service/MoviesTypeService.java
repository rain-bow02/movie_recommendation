package com.example.shopserve.service;

import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.MoviesType;
import com.example.shopserve.result.Pagination;
import com.example.shopserve.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface MoviesTypeService {
    List<Movies> selectAllTypes();

    List<MoviesType> getMoviesTypeByPage(int page);

    List<MoviesType> getMoviesTypeByPageSize(int page, int size);

//    Result<Pagination<Movies>> getMoviesByPage(int page, int size);
}
