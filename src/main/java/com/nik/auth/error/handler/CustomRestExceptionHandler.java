package com.nik.auth.error.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;
import com.nik.auth.error.exceptions.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.nik.auth")
public class CustomRestExceptionHandler{

    @ExceptionHandler
    public ResponseEntity<ResultData<Void>> handle(Exception ex) {

        ResultData<Void> resultData = new ResultData<>();
        resultData.setResultType(ResultType.FAIL);
        resultData.setMsg("에러발생! 관리자에게 문의해주세요.");
        log.error("에러 발생!", ex);
        return new ResponseEntity<>(resultData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<Map<String,Object>>>> handle(MethodArgumentNotValidException ex) {

        String firstMessage = null;

        List<Map<String,Object>> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {

            if(firstMessage == null) firstMessage = error.getDefaultMessage();

            Map<String,Object> errorMap = new HashMap<>();
            errorMap.put("field", error.getField());
            errorMap.put("message", error.getDefaultMessage());
            errors.add(errorMap);
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {

            if(firstMessage == null) firstMessage = error.getDefaultMessage();

            Map<String,Object> errorMap = new HashMap<>();
            errorMap.put("field", error.getObjectName());
            errorMap.put("message", error.getDefaultMessage());
            errors.add(errorMap);
        }

        ResultData<List<Map<String,Object>>> resultData = new ResultData<>();
        resultData.setResultType(ResultType.FAIL);
        resultData.setMsg(firstMessage);
        resultData.setData(errors);
        log.error("에러 발생!", ex);
        return new ResponseEntity<>(resultData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResultData<Void>> handle403(Exception ex) {
        ResultData<Void> resultData = new ResultData<>();
        resultData.setResultType(ResultType.FAIL);
        resultData.setMsg("접근권한이 없습니다.");
        log.error("에러 발생!", ex);
        return new ResponseEntity<>(resultData, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResultData<Void>> handle401(Exception ex) {
        ResultData<Void> resultData = new ResultData<>();
        resultData.setResultType(ResultType.FAIL);
        resultData.setMsg("로그인정보가 일치하지 않습니다.");
        log.error("에러 발생!", ex);
        return new ResponseEntity<>(resultData, HttpStatus.FORBIDDEN);
    }


}
