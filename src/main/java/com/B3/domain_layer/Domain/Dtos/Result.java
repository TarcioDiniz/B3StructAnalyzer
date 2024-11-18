package com.B3.domain_layer.Domain.Dtos;

public class Result {
    public String message;
    public Boolean status;
    public Object data;

    public Result(String message, boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
