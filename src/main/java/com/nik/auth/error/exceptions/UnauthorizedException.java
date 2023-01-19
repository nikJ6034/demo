package com.nik.auth.error.exceptions;

import javax.servlet.ServletException;

public class UnauthorizedException extends ServletException{

    private static final long serialVersionUID = 1L;

    public UnauthorizedException(String msg) {
        super(msg);
    }

}
