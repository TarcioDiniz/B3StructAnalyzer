package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.utils.ComparatorProvider;

import java.util.function.BiFunction;

public class QuickSortMedianOfThree<T> implements Algorithms<T> {

    @Override
    public String getName() {
        return "QuickSort_With_Median_of_Three";
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
        int median = medianOfThree(array, low, high, comparator);
        swap(array, median, high);

        T pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparator.apply(array[j], pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);

        return i + 1;
    }

    private int medianOfThree(T[] array, int low, int high, BiFunction<T, T, Integer> comparator) {
        int mid = (low + high) / 2;

        if (comparator.apply(array[low], array[mid]) > 0) {
            swap(array, low, mid);
        }
        if (comparator.apply(array[low], array[high]) > 0) {
            swap(array, low, high);
        }
        if (comparator.apply(array[mid], array[high]) > 0) {
            swap(array, mid, high);
        }

        return mid;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public String getComplexity() {
        return "O(n log n) average, O(n^2) worst case, but reduced likelihood of worst case due to median-of-three pivot selection.";
    }
}
