package com.haynes.identifly.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNKNOWN_ERROR(999,"LỖI KHÔNG RÕ", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001,"Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002,"Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005,"Người duùng không tồn tại",HttpStatus.NOT_FOUND),
    USER_NAME_INVALID(103,"Tên người dùng phải lớn hơn 5 kí tự", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(104,"Mật khẩu phải lớn hơn 8 kí tự",HttpStatus.BAD_REQUEST),
    INVALID_CODE(105,"Invalid massged code", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(106,"Đăng nhập không thành công",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(107,"Bạn không có quyền",HttpStatus.FORBIDDEN)
    ;
    ErrorCode(int code, String message,HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

}
