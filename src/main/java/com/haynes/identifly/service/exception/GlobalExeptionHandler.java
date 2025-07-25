package com.haynes.identifly.service.exception;

import com.haynes.identifly.service.dto.request.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler {


    @ExceptionHandler(value= Exception.class)
    ResponseEntity<APIResponse> handlingRuntimeException(RuntimeException runtimeException){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(ErrorCode.UNKNOWN_ERROR.getCode());
        apiResponse.setMessage(ErrorCode.UNKNOWN_ERROR.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value= AppException.class)
    ResponseEntity<APIResponse> handlingAppException(AppException runtimeException){
        ErrorCode errorCode = runtimeException.getErrorCode();
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse> handlingValidation(MethodArgumentNotValidException methodArgumentNotValidException){
        String enumKey = methodArgumentNotValidException.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_CODE;
        try{
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException e){

        }

        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
