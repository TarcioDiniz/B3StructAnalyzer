package com.B3.application_layer.Controllers;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.DomainArrayList.Services.IArrayListService;

public class ArrayListController<T> {

    private final IArrayListService<T> arrayListService;

    public ArrayListController(IArrayListService<T> arrayListService) {
        this.arrayListService = arrayListService;
    }

    public Result sorting(AlgorithmsEnum algorithms, T array) {
        return arrayListService.sort(algorithms, array);
    }
}
