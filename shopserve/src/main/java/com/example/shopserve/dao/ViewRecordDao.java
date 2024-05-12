package com.example.shopserve.dao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.ViewRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Movies)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Mapper
public interface ViewRecordDao {

    List<Movies> getMoviesSeen(int user_id,int offset, int size);

    void saveViewRecord(ViewRecord viewRecord);

    void deleteViewRecord(ViewRecord viewRecord);

    void deleteMovies(int movieId);

    ViewRecord hasViewRecord(ViewRecord viewRecord);

    void updateViewRecord(ViewRecord viewRecord);

    int selectMoviesSeenLength(int user_id);
}
