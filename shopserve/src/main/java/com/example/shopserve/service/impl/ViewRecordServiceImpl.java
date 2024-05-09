package com.example.shopserve.service.impl;

import com.example.shopserve.dao.*;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Rating;
import com.example.shopserve.entity.Recommend;
import com.example.shopserve.entity.ViewRecord;
import com.example.shopserve.result.SimilarityUtil;
import com.example.shopserve.service.MoviesService;
import com.example.shopserve.service.StarsService;
import com.example.shopserve.service.ViewRecordService;
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
@Service("viewRecordService")
public class ViewRecordServiceImpl implements ViewRecordService {

    @Resource
    private ViewRecordDao viewRecordDao;
    @Resource
    private MoviesDao moviesDao;

    @Override
    public List<Movies> getMoviesSeen(int userId) {
        List<Movies> shopList = this.viewRecordDao.getMoviesSeen(userId);
        return shopList;
    }

    @Override
    public void saveViewRecord(ViewRecord viewRecord) {
        this.viewRecordDao.saveViewRecord(viewRecord);
    }

    @Override
    public void deleteViewRecord(ViewRecord viewRecord) {
        this.viewRecordDao.deleteViewRecord(viewRecord);
    }
}
