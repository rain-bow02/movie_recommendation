package com.example.shopserve.service.impl;

import com.example.shopserve.dao.MoviesDao;
import com.example.shopserve.dao.MoviesTypeDao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.MoviesType;
import com.example.shopserve.result.Pagination;
import com.example.shopserve.service.MoviesTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("moviesTypeService")
public class MoviesTypeServiceImpl implements MoviesTypeService {

    @Resource
    private MoviesDao moviesDao;
    @Resource
    private MoviesTypeDao moviesTypeDao;
    @Override
    public List<Movies> selectAllTypes() {
        return null;
    }

    @Override
    public List<MoviesType> getMoviesTypeByPage(int page) {
       int offset=(page-1)*10;
        List<MoviesType> shopList = this.moviesTypeDao.getMoviesTypePagination(offset,10);
        return shopList;
    }

    @Override
    public List<MoviesType> getMoviesTypeByPageSize(int page, int size) {
        int offset=(page-1)*size;
        List<MoviesType> shopList = this.moviesTypeDao.getMoviesTypePagination(offset,size);
        return shopList;
    }
}
