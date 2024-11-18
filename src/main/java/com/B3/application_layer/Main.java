package com.B3.application_layer;

import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.domain_layer.Domain.Dtos.Result;

public class Main {

    public static void main(String[] args) {

        ResultRepository repository = new ResultRepository();
        Result result = repository.result("Operation completed", true, "Data payload");
        System.out.println(result.message);
    }
}
