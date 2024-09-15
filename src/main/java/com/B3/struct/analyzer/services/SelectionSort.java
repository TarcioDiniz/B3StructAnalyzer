package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.utils.ComparatorProvider;

import java.util.function.BiFunction;

public class SelectionSort<T> implements Algorithms<T> {

    @Override
    public String getName() {
        return "Selection Sort";
    }

    @Override
    public void sort(T[] array) {
        sort(array, ComparatorProvider.getDefaultComparator(array));
    }

    @Override
    public void sort(T[] array, BiFunction<T, T, Integer> comparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.apply(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    @Override
    public String getComplexity() {
        return "O(n^2)";
    }
}
