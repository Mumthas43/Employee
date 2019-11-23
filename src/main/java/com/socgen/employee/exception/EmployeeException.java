package com.socgen.employee.exception;

public class EmployeeException extends RuntimeException{

    ErrorConstant errorConstant;

    public EmployeeException(ErrorConstant errorConstant) {
        this.errorConstant = errorConstant;
    }

    public ErrorConstant getErrorConstant() {
        return errorConstant;
    }
}
