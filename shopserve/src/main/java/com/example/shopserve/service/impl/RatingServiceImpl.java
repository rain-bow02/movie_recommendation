package com.example.shopserve.service.impl;
import com.example.shopserve.dao.RatingDao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Rating;
import com.example.shopserve.service.RatingService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * (Movies)表服务实现类
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Service("ratingService")
public class RatingServiceImpl implements RatingService {
    @Resource
    private RatingDao ratingDao;
    @Override
    public void SaveRating(Rating rating){

        System.out.println("RatingServiceImplokok");
        this.ratingDao.SaveRating(rating);
    }
    @Override
    public List<Movies> showRatingByUserId(int userId) {
        return ratingDao.showRatingByUserId(userId);
    }

    @Override
    public int selectStarredMoviesLength(int userId) {
        int length = this.ratingDao.selectStarredMoviesLength(userId);
        return length;
    }
}
