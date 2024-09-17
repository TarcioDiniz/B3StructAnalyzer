package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.utils.ComparatorProvider;

import java.util.function.BiFunction;

public class HeapSort<T> implements Algorithms<T> {

    @Override
    public String getName() {
        return "HeapSort";
    }

    @Override
    public void sort(T[] array) {
        sort(array, ComparatorProvider.getDefaultComparator(array));
    }

    @Override
    public void sort(T[] array, BiFunction<T, T, Integer> comparator) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, comparator);
        }

        for (int i = n - 1; i >= 0; i--) {
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0, comparator);
        }
    }

    private void heapify(T[] array, int n, int i, BiFunction<T, T, Integer> comparator) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparator.apply(array[left], array[largest]) > 0) {
            largest = left;
        }

        if (right < n && comparator.apply(array[right], array[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            T swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest, comparator);
        }
    }

    @Override
    public String getComplexity() {
        return "O(n log n)";
    }
}
