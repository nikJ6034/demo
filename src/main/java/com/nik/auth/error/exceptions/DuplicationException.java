package com.nik.auth.error.exceptions;

public class DuplicationException extends Exception{

    private static final long serialVersionUID = 1L;

    DuplicationException(String msg){
        super(msg);
    }

}
