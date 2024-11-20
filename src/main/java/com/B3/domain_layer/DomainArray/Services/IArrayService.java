package com.B3.domain_layer.DomainArray.Services;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;

import java.util.Comparator;

public interface IArrayService<T> {
    public Result sort(AlgorithmsEnum algorithm, T array, Comparator<Object> comparator);
}
