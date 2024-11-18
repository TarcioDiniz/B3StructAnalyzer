package com.B3.domain_layer.DomainArrayList.Services;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;

public interface IArrayListService<T> {
    public Result sort(AlgorithmsEnum algorithms, T array);
}
