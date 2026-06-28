package com.volunteer.exception;

public class ServiceException extends RuntimeException {
    private int errorCode;
    public ServiceException(String message) {
        super(message);
        this.errorCode = 500;
    }
    public ServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public int getErrorCode() { return errorCode; }
}
