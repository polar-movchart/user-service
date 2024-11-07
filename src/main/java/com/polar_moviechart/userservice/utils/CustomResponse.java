package com.polar_moviechart.userservice.utils;

import com.polar_moviechart.userservice.exception.ErrorInfo;
import lombok.Getter;

import java.util.Optional;

@Getter
public class CustomResponse<T> {
    private Boolean isSuccess = true;
    private String ErrorMsg = null;
    private String code = null;
    private Optional<T> data;

    public CustomResponse(T data) {
        this.data = Optional.ofNullable(data);
    }

    public void setCode(ErrorInfo errorInfo) {
        this.ErrorMsg = errorInfo.getMessage();
        this.code = errorInfo.getCode();
        this.isSuccess = false;
    }

    public void setCode(String errorCode) {
        this.code = errorCode;
    }
}
