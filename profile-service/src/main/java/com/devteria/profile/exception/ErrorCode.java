package com.devteria.profile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(2001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(2002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(2003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(2004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(2005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(2006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(2007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(2008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    THANH_CONG(1000, "Thành công", HttpStatus.OK),
    NULL(2009, "Có thứ gì đó null", HttpStatus.BAD_REQUEST),
    KHONG_TIM_THAY_PROFILE(20010, "Không tìm thấy profile", HttpStatus.NOT_FOUND),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
