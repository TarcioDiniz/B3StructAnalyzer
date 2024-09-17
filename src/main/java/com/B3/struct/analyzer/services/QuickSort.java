package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.utils.ComparatorProvider;

import java.util.function.BiFunction;

public class QuickSort<T> implements Algorithms<T> {

    @Override
    public String getName() {
        return "QuickSort";
    }

    @Override
    public void sort(T[] array) {
        sort(array, ComparatorProvider.getDefaultComparator(array));
    }

    @Override
    public void sort(T[] array, BiFunction<T, T, Integer> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }

    private void quickSort(T[] array, int low, int high, BiFunction<T, T, Integer> comparator) {
        if (low < high) {
            int pi = partition(array, low, high, comparator);
            quickSort(array, low, pi - 1, comparator);
            quickSort(array, pi + 1, high, comparator);
        }
    }

    private int partition(T[] array, int low, int high, BiFunction<T, T, Integer> comparator) {
        T pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparator.apply(array[j], pivot) < 0) {
                i++;

                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    @Override
    public String getComplexity() {
        return "O(n log n) average, O(n^2) worst case";
    }
}
