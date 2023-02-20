package com.springsecurity.model;

public class OutputModel {
    private String errorMessage;
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public OutputModel(String errorMessage, Employee employee) {
        this.errorMessage = errorMessage;
        this.employee = employee;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public OutputModel() {

    }


}

