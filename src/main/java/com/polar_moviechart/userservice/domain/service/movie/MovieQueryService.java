package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.dto.MovieReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieQueryService {

    private final MovieReviewQueryService movieReviewQueryService;
    private final MovieRatingQueryService movieRatingQueryService;

    public Double getUserMovieRating(int code, Long userId) {
        return movieRatingQueryService.getUserMovieRating(code, userId);
    }

    public MovieReviewRes getReview(Long userId, int code) {
        return movieReviewQueryService.getReview(userId, code);
    }
}
