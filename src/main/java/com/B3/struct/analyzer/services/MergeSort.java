package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.utils.ComparatorProvider;

import java.util.function.BiFunction;

public class MergeSort<T> implements Algorithms<T> {

    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public void sort(T[] array) {
        sort(array, ComparatorProvider.getDefaultComparator(array));
    }

    @Override
    public void sort(T[] array, BiFunction<T, T, Integer> comparator) {
        mergeSort(array, 0, array.length - 1, comparator);
    }

    private void mergeSort(T[] array, int left, int right, BiFunction<T, T, Integer> comparator) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid, comparator);
            mergeSort(array, mid + 1, right, comparator);

            merge(array, left, mid, right, comparator);
        }
    }

    private void merge(T[] array, int left, int mid, int right, BiFunction<T, T, Integer> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        @SuppressWarnings("unchecked")
        T[] leftArray = (T[]) new Object[n1];
        @SuppressWarnings("unchecked")
        T[] rightArray = (T[]) new Object[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (comparator.apply(leftArray[i], rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    @Override
    public String getComplexity() {
        return "O(n log n)";
    }
}
