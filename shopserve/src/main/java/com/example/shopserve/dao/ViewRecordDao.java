package com.example.shopserve.dao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.ViewRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Movies)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Mapper
public interface ViewRecordDao {

    List<Movies> getMoviesSeen(int userId);

    void saveViewRecord(ViewRecord viewRecord);

    void deleteViewRecord(ViewRecord viewRecord);
}
