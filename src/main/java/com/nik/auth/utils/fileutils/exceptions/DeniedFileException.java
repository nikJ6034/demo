package com.nik.auth.utils.fileutils.exceptions;

public class DeniedFileException extends Exception{

    private static final long serialVersionUID = 1L;

    public DeniedFileException() {
        super();
    }

    public DeniedFileException(String message) {
        super(message);
    }

}
