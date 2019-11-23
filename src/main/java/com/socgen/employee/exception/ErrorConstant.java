package com.socgen.employee.exception;

public enum ErrorConstant {
    UNPARSABLE_DATE(2002,"Date Format Not Supported. Supported Format is DD-MM-YYYY");

    private int errorCode;
    private String errorMessage;


    ErrorConstant(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorConstant{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
