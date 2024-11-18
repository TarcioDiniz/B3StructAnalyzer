package com.B3.business_layer.Business.Repositories;

import com.B3.domain_layer.Domain.Repositories.IWriteArrayRepository;

public class WriteArrayRepository<T> implements IWriteArrayRepository<T> {

    @Override
    public void writeFile(String fileName, T array) {

    }

    @Override
    public T readFile(String fileName) {
        return null;
    }
}
