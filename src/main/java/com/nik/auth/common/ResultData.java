package com.nik.auth.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultData<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    private ResultType resultType;
    private String msg;
    private T data;
}
