package com.example.shopserve.service;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Stars;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface StarsService {

    List<Movies> getStarredMovies(int userId, int page);

    void saveStars(Stars stars);

    void deleteStars(Stars stars);

    void deleteMovies(int movieId);

    Stars hasStars(Stars stars);

    int selectStarredMoviesLength(int userId);
}
