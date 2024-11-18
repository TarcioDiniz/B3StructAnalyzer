package com.B3.domain_layer.DomainArray.Services;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;

public interface IArrayService<T> {
    public Result sort(AlgorithmsEnum algorithms, T array);
}
