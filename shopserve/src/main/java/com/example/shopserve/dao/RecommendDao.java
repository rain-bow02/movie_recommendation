package com.example.shopserve.dao;
import com.example.shopserve.entity.Rating;
import com.example.shopserve.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.List;

/**
 * (Movies)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Mapper
public interface RecommendDao {
    List <Recommend> getAllRecommend();


    List<Recommend> getRecommendByUserId(int userId);
}
