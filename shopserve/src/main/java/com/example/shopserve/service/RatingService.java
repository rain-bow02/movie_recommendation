package com.example.shopserve.service;

import com.example.shopserve.entity.Rating;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service

@Transactional(propagation= Propagation.REQUIRES_NEW)
public interface RatingService {
    void SaveRating(Rating rating);
    List<Rating> showRatingByUserId(int userId);
}
