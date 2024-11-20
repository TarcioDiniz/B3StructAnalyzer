package com.B3.application_layer.Controllers;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.DomainArray.Services.IArrayService;

public class ArrayController<T> {

    private final IArrayService<T> arrayService;

    public ArrayController(IArrayService<T> arrayService) {
        this.arrayService = arrayService;
    }


    public Result sorting(AlgorithmsEnum algorithm, T array) {
        return arrayService.sort(algorithm, array);
    }
}
