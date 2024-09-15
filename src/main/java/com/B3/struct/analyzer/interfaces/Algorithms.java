package com.B3.struct.analyzer.interfaces;

import java.util.function.BiFunction;

public interface Algorithms<T> {
    public String getName();

    public void sort(T[] array);

    void sort(T[] array, BiFunction<T, T, Integer> comparator);

    public String getComplexity();
}
