package com.nik.auth.error.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;

@RestController
public class CustomErrorController {


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @RequestMapping(value = "/auth/error")
    public ResultData<Void> authError(HttpServletRequest request ,Exception ex){

        String errorMsg = (String)request.getAttribute("errorMsg");

        ResultData<Void> resultData = new ResultData<>();
        resultData.setResultType(ResultType.FAIL);
        resultData.setMsg(errorMsg);

        return resultData;
    }

}
