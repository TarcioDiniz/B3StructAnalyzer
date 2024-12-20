package com.B3.application_layer.Controllers;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.DomainArray.Services.IArrayService;

import java.util.Comparator;

public class SortController<T> {

    private final IArrayService<T> arrayService;

    public SortController(IArrayService<T> arrayService) {
        this.arrayService = arrayService;
    }


    public Result sorting(AlgorithmsEnum algorithm, T array, Comparator<Object> comparator) {
        return arrayService.sort(algorithm, array, comparator);
    }
}
