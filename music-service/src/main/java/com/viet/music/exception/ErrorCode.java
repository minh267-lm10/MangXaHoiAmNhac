package com.viet.music.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1101, "Uncategorized error", HttpStatus.BAD_REQUEST),
    RONG_HOAC_NULL(1102, "Có cái gì đó rỗng hoặc null -_-", HttpStatus.BAD_REQUEST),
    BAI_HAT_DA_TON_TAI(1103, "Bài hát đã tồn tại", HttpStatus.CONFLICT),
    KHONG_TON_TAI_PLAYLIST(1104, "Không tìm thấy Playlist", HttpStatus.NOT_FOUND),
    KHONG_TON_TAI_BAI_HAT(1105, "Không tìm thấy bài hát nào", HttpStatus.NOT_FOUND),
    KHONG_TON_TAI(1105, "Không tồn tại", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1107, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1108, "You do not have permission", HttpStatus.FORBIDDEN),
    LOI_KHONG_LUU_DUOC(1109, "Lưu dữ liệu thất bại", HttpStatus.BAD_REQUEST),
    PLAYLIST_KHONG_PHAI_CUU_CUA_BAN(1110, "Playlist này không được tạo bởi bạn", HttpStatus.BAD_REQUEST),
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
