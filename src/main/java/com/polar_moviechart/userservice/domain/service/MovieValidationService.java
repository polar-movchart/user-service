package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import com.polar_moviechart.userservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@RequiredArgsConstructor
public class MovieValidationService {
    private final RestTemplate restTemplate;

    @Value("${movie-service.url}")
    private String movieServiceUrl;

    public void validateMovieExists(Integer movieCode) {
        String requestUrl = movieServiceUrl + movieCode;
        Boolean isMovieExists = restTemplate.getForEntity(requestUrl, CustomResponse.class)
                .getBody()
                .getIsSuccess();
        if (!isMovieExists) {
            throw new UserBusinessException(ErrorCode.MOVIE_NOT_EXISTS);
        }
    }
}
