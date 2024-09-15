package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.utils.ComparatorProvider;

import java.util.function.BiFunction;

public class InsertionSort<T> implements Algorithms<T> {

    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public void sort(T[] array) {
        sort(array, ComparatorProvider.getDefaultComparator(array));
    }

    @Override
    public void sort(T[] array, BiFunction<T, T, Integer> comparator) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            T key = array[i];
            int j = i - 1;

            // Move elements of array[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && comparator.apply(array[j], key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    @Override
    public String getComplexity() {
        return "O(n^2)";
    }
}
