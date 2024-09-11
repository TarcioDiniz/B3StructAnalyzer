package com.B3.struct.analyzer.interfaces;

import java.util.Comparator;

public interface Algorithms<T> {
    public String getName();

    public T[] sort(T[] array, Comparator<? super T> comparator);

    public String getComplexity();
}