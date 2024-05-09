package com.example.shopserve.service;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Stars;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public interface StarsService {

    List<Movies> getStarredMovies(int userId);

    void saveStars(Stars stars);

    void deleteStars(Stars stars);
}
