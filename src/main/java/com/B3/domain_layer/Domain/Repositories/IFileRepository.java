package com.B3.domain_layer.Domain.Repositories;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.CsvColumnEnum;

public interface IFileRepository<T> {
    public Result writeFile(String fileName, T array);

    public T readFile(String fileName);

    public String getColumnValue(T row, CsvColumnEnum column);
}
