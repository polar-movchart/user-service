package com.polar_moviechart.userservice.controller.publicapi;

import com.polar_moviechart.userservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import com.polar_moviechart.userservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/v1/users/movies")
public class MovieControllerPublic {

    private final MovieQueryService movieQueryService;

    @GetMapping("/{code}/reviews")
    public ResponseEntity<CustomResponse<List<MovieReviewRes>>> getReviews(
            HttpServletRequest servletRequest,
            @PathVariable("code") int code,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<MovieReviewRes> res = movieQueryService.getReviews(getUserId(servletRequest), code, pageRequest);

        return ok(new CustomResponse<>(res));
    }

    private Long getUserId(HttpServletRequest servletRequest) {
        return Long.parseLong(servletRequest.getHeader("X-User-Id"));
    }
}
