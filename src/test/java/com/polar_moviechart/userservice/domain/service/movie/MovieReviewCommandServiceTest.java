package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.MovieReviewTestConfig;
import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateMovieReviewReq;
import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieReviewCommandServiceTest extends MovieReviewTestConfig {

    @Autowired
    private MovieReviewCommandService movieReviewCommandService;

    @BeforeEach
    void setUp() {
        initUsers(1);
    }

    @DisplayName("기존 리뷰가 없을 때 영화 리뷰를 작성할 수 있다.")
    @Test
    void updateReviewTest_whenReviewDoesNotExists() {
        // given
        int movieCode = 1;
        String content = "content";
        // when
        UpdateMovieReviewReq req = UpdateMovieReviewReq.builder()
                .content(content)
                .build();
        movieReviewCommandService.addReview(movieCode, getUser(0), req);
        // then
        assertNotNull(movieReviewRepository.findByUser_IdAndCode(getUserId(0), movieCode));
    }
}