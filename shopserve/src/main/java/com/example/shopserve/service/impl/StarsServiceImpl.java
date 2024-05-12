package com.example.shopserve.service.impl;

import com.example.shopserve.dao.MoviesDao;
import com.example.shopserve.dao.StarsDao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Stars;
import com.example.shopserve.service.StarsService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;

/**
 * (Movies)表服务实现类
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Service("starsService")
public class StarsServiceImpl  implements StarsService {

    @Resource
    private StarsDao starsDao;
    @Resource
    private MoviesDao moviesDao;

    @Override
    public List<Movies> getStarredMovies(int userId, int page) {
        int offset=(page-1)*20;
        List<Movies> shopList = this.starsDao.getStarredMovies(userId,offset,20);
//        return shopList;
        return shopList;
    }
    @Override
    public void saveStars(Stars stars) {
        this.starsDao.saveStars(stars);
    }

    @Override
    public void deleteStars(Stars stars) {
        this.starsDao.deleteStars(stars);
    }

    @Override
    public void deleteMovies(int movie_id) {
        this.starsDao.deleteMovies(movie_id);

    }

    @Override
    public Stars hasStars(Stars stars) {
        return this.starsDao.hasStars(stars);

    }

    @Override
    public int selectStarredMoviesLength(int userId) {
        int length = this.starsDao.selectStarredMoviesLength(userId);
        return length;
    }

}
