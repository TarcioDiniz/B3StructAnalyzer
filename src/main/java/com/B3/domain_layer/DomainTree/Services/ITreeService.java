package com.B3.domain_layer.DomainTree.Services;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;

public interface ITreeService<T> {
    public Result sort(AlgorithmsEnum algorithms, T array);
}
