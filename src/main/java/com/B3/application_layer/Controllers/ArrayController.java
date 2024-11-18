package com.B3.application_layer.Controllers;

import com.B3.business_layer.Business.Services.ArrayService;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;

public class ArrayController<T> {

    private final ArrayService<T> arrayService;

    public ArrayController(ArrayService<T> arrayService) {
        this.arrayService = arrayService;
    }

    public Result sorting(AlgorithmsEnum algorithm, T array) {
        return arrayService.sort(algorithm, array);
    }
}
