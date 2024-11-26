package com.polar_moviechart.userservice.domain.controller.secureapi;

import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateMovieLikeReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateMovieReviewReq;
import com.polar_moviechart.userservice.domain.controller.secureapi.dtos.UpdateRatingRequest;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieRatingRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikeRes;
import com.polar_moviechart.userservice.domain.service.movie.dtos.UpdateReviewRes;
import com.polar_moviechart.userservice.domain.service.movie.MovieCommandService;
import com.polar_moviechart.userservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.userservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/api/v1/users/movies")
public class MovieControllerSecure {

    private final MovieQueryService movieQueryService;
    private final MovieCommandService movieCommandService;

    @PostMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> updateRating(HttpServletRequest servletRequest,
                                                               @PathVariable(name = "code") int code,
                                                               @RequestBody UpdateRatingRequest updateRatingRequest) {

        double ratingValue = movieCommandService.updateRating(code, getUserId(servletRequest), updateRatingRequest);

        return ok(new CustomResponse<>(ratingValue));
    }

    @GetMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> getMovieRating(HttpServletRequest request,
                                                                 @PathVariable(name = "code") int code) {
        Long userId = (Long) request.getAttribute("userId");
        Double movieRating = movieQueryService.getUserMovieRating(code, userId);

        return ok(new CustomResponse<>(movieRating));
    }

    @PostMapping("/{code}/reviews")
    public ResponseEntity<CustomResponse<UpdateReviewRes>> updateReview(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code,
            @Valid @RequestBody UpdateMovieReviewReq req) {
        UpdateReviewRes updateReviewRes = movieCommandService.updateReview(code, getUserId(servletRequest), req);

        return ok(new CustomResponse<>(updateReviewRes));
    }

    @GetMapping("/{code}/reviews")
    public ResponseEntity<CustomResponse<MovieReviewRes>> getReview(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code) {

        MovieReviewRes res = movieQueryService.getReview(getUserId(servletRequest), code);

        return ok(new CustomResponse<>(res));
    }

    @PostMapping("/{code}/likes")
    public ResponseEntity<CustomResponse<MovieLikeRes>> updateLike(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code,
            @RequestBody UpdateMovieLikeReq req) {
        MovieLikeRes movieLikeRes = movieCommandService.updateLike(getUserId(servletRequest), code, req);

        return ok(new CustomResponse<>(movieLikeRes));
    }

    @GetMapping("/{code}/likes")
    public ResponseEntity<CustomResponse<MovieLikeRes>> getLike(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code) {
        MovieLikeRes movieLikeRes = movieQueryService.getLike(getUserId(servletRequest), code);

        return ok(new CustomResponse<>(movieLikeRes));
    }

    // TODO: pageable로 변경
    @GetMapping("/reviews")
    public ResponseEntity<CustomResponse<List<MovieReviewRes>>> getUserMovieReviews(
            HttpServletRequest servletRequest) {
        Long userId = getUserId(servletRequest);
        List<MovieReviewRes> reviews = movieQueryService.getUserMovieReviews(userId);
        return ok(new CustomResponse<>(reviews));
    }

    // TODO: pageable로 변경
    @GetMapping("/likes")
    public ResponseEntity<CustomResponse<List<MovieLikeRes>>> getUserMovieLikes(
            HttpServletRequest servletRequest) {
        Long userId = getUserId(servletRequest);
        List<MovieLikeRes> likes = movieQueryService.getUserMovieLikes(userId);
        return ok(new CustomResponse<>(likes));
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<MovieRatingRes>>> getUserMovieRatings(
            HttpServletRequest servletRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        List<MovieRatingRes> ratings = movieQueryService
                .getUserMovieRatings(getUserId(servletRequest), pageable);
        return ok(new CustomResponse<>(ratings));
    }

    private Long getUserId(HttpServletRequest servletRequest) {
        return Long.parseLong(servletRequest.getHeader("X-User-Id"));
    }
}
