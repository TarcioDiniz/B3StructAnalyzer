package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;

import java.util.Comparator;

public class BubbleSort<T> implements Algorithms<T> {
    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public T[] sort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    @Override
    public String getComplexity() {
        return "O(n^2)";
    }
}
