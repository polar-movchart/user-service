package com.polar_moviechart.userservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DEFAULT_ERROR("9999", "예기치 못한 오류가 발생했습니다." + "\n" + "불편을 드려 죄송합니다."),
    TOKEN_CREATION_ERROR("100", "로그인 중 문제가 발생했습니다."),
    NOT_ALLOWED("104", "접근 권한이 없습니다."),
    TOKEN_EXPIRED("T101", "토큰이 만료되었습니다."),
    TOKEN_CANNOT_BE_NULL("U102", "토큰이 전달되지 않았습니다.");

    private String code = "U101";
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorCode(String message) {
        this.message = message;
    }
}
