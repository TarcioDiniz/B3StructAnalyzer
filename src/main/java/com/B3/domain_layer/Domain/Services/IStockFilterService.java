package com.B3.domain_layer.Domain.Services;

import com.B3.domain_layer.Domain.Dtos.Result;

public interface IStockFilterService {
    Result filterByMaxVolume(String[] data);
}
