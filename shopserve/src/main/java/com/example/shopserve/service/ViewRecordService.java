package com.example.shopserve.service;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.ViewRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public interface ViewRecordService {

    List<Movies> getMoviesSeen(int userId, int page);

    void saveViewRecord(ViewRecord viewRecord);

    void deleteViewRecord(ViewRecord viewRecord);

    void deleteMovies(int movieId);

    ViewRecord hasViewRecord(ViewRecord viewRecord);

    void updateViewRecord(ViewRecord viewRecord);

    int selectMoviesSeenLength(int userId);
}
