package com.haynes.identifly.service.exception;

public enum ErrorCode {
    UNKNOWN_ERROR(999,"LỖI KHÔNG RÕ"),
    USER_EXISTED(1002,"Người dùng đã tồn tại"),
    USER_NOT_EXISTED(1005,"Người duùng không tồn tại"),
    USER_NAME_INVALID(103,"Tên người dùng phải lớn hơn 5 kí tự"),
    PASSWORD_INVALID(104,"Mật khẩu phải lớn hơn 8 kí tự"),
    INVALID_CODE(105,"Invalid massged code"),
    UNAUTHENTICATED(106,"Đăng nhập không thành công")
    ;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
