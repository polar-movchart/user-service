package com.polar_moviechart.userservice.domain.dto;

import lombok.Builder;

public class MovieLikeMessageDto implements MessageDto {
    private Long userId;
    private Integer movieCode;
    private Integer likeCnt;

    @Builder
    public MovieLikeMessageDto(Long userId, Integer movieCode, Integer likeCnt) {
        this.userId = userId;
        this.movieCode = movieCode;
        this.likeCnt = likeCnt;
    }
    @Override
    public String getType() {
        return "MOVIE_LIKE";
    }
}