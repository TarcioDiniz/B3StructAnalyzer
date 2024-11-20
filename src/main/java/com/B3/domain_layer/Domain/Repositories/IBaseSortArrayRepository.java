package com.B3.domain_layer.Domain.Repositories;

import java.util.Comparator;

public interface IBaseSortArrayRepository<T> {
    public T sort(T array, Comparator<Object> comparator);
}
