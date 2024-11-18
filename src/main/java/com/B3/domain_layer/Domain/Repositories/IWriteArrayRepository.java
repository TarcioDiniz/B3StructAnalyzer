package com.B3.domain_layer.Domain.Repositories;

public interface IWriteArrayRepository<T> {
    public void writeFile(String fileName, T array);

    public T readFile(String fileName);
}
