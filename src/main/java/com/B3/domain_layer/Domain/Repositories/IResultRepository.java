package com.B3.domain_layer.Domain.Repositories;

import com.B3.domain_layer.Domain.Dtos.Result;

public interface IResultRepository {
    public Result result(String message, Boolean status, Object data);
}
