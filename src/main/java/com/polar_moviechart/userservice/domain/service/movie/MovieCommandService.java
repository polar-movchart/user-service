package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.AddReviewReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateLikeReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import com.polar_moviechart.userservice.domain.service.UserQueryService;
import com.polar_moviechart.userservice.domain.service.movie.dtos.AddReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieCommandService {

    private final MovieReviewCommandService movieReviewCommandService;
    private final MovieRatingCommandService movieRatingCommandService;
    private final MovieLikeCommandService movieLikeCommandService;
    private final MovieQueryService movieQueryService;
    private final UserQueryService userQueryService;


    @Transactional
    public double updateRating(int code, Long userId, UpdateRatingRequest updateRatingRequest) {
        movieQueryService.validateMovieExists(code);
        return movieRatingCommandService.updateRating(code, getUser(userId), updateRatingRequest);
    }

    @Transactional
    public AddReviewRes addReview(int code, Long userId, AddReviewReq req) {
        movieQueryService.validateMovieExists(code);
        return movieReviewCommandService.addReview(code, getUser(userId), req);
    }

    @Transactional
    public MovieLikeRes updateLike(Long userId, int code, UpdateLikeReq req) {
        movieQueryService.validateMovieExists(code);
        MovieLike movieLike = movieLikeCommandService.updateLike(code, getUser(userId), req);
        return movieLike.toDto();
    }

    private User getUser(Long userId) {
        return userQueryService.getKakaoUser(AuthType.KAKAO, userId);
    }
}