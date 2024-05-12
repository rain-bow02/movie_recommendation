package com.example.shopserve.service.impl;

import com.example.shopserve.dao.MoviesDao;
import com.example.shopserve.dao.MoviesTypeDao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.MoviesType;
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
    public int selectTypesLength() {
        int length = this.moviesTypeDao.selectTypesLength();
        return length;
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

    @Override
    public MoviesType getMoviesTypeByName(String name) {
        MoviesType moviesType=this.moviesTypeDao.getMoviesTypeByName(name);
        return moviesType;
    }

    @Override
    public MoviesType insertType(MoviesType moviesType) {
        this.moviesTypeDao.insertType(moviesType);
        return moviesTypeDao.getMoviesTypeByName(moviesType.getName());
    }

    @Override
    public MoviesType getMoviesTypeById(int id) {
        MoviesType moviesType=this.moviesTypeDao.getMoviesTypeById(id);
        return moviesType;
    }

    @Override
    public MoviesType updateType(MoviesType moviesType) {
        this.moviesTypeDao.updateType(moviesType);
        return moviesTypeDao.getMoviesTypeByName(moviesType.getName());
    }

    @Override
    public void deleteType(int id) {
        this.moviesTypeDao.deleteType(id);
    }

    @Override
    public void insertType_movie(int movieId, int typeId) {
        int relation=deleteRelation(movieId);
        System.out.println("删除了" +relation);
            this.moviesTypeDao.insertType_movie(movieId, typeId);
    }

    @Override
    public int deleteRelation(int movieId) {
        return this.moviesTypeDao.deleteRelation(movieId);
    }


    @Override
    public void insertMovieRelation(Movies movies1) {
        //新增电影类别
        System.out.println("新增电影类别" + movies1.getGenre());
        String[] names;
        if (movies1.getGenre().contains("|")) {
            names = movies1.getGenre().split("\\|");
        } else names = new String[]{movies1.getGenre()};

        for (int index = 0; index < names.length; index++) {
            int typeId;
            int movieId = movies1.getId();
            System.out.println(movieId + "movieId" + names[0]);
            MoviesType type = getMoviesTypeByName(names[index]);
            if (type == null) {
                MoviesType type1 = new MoviesType();
                System.out.println(type1 + names[index]);
                type1.setName(names[index]);
                typeId = insertType(type1).getId();
                System.out.println(typeId + "typeId");
            } else {
                typeId = updateType(type).getId();
            }
            System.out.println("新增电影_类别" + movieId + typeId);
            //新增电影_类型 电影id 类别id
            insertType_movie(movieId, typeId);
        }
    }

}
