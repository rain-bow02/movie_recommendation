package com.example.shopserve.service.impl;

import com.example.shopserve.dao.MoviesDao;
import com.example.shopserve.dao.RatingDao;
import com.example.shopserve.dao.RecommendDao;
import com.example.shopserve.dao.StarsDao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Rating;
import com.example.shopserve.entity.Recommend;
import com.example.shopserve.entity.Stars;
import com.example.shopserve.result.SimilarityUtil;
import com.example.shopserve.service.MoviesService;
import com.example.shopserve.service.StarsService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Movies> getStarredMovies(int userId) {
        List<Movies> shopList = this.starsDao.getStarredMovies(userId);
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

}
