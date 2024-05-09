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

    List<Movies> getMoviesSeen(int userId);

    void saveViewRecord(ViewRecord viewRecord);

    void deleteViewRecord(ViewRecord viewRecord);
}
