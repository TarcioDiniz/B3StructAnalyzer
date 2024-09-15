package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.utils.ComparatorProvider;

import java.util.function.BiFunction;

public class HeapSort<T> implements Algorithms<T> {

    @Override
    public String getName() {
        return "Heap Sort";
    }

    @Override
    public void sort(T[] array) {
        sort(array, ComparatorProvider.getDefaultComparator(array));
    }

    @Override
    public void sort(T[] array, BiFunction<T, T, Integer> comparator) {
        int n = array.length;

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, comparator);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Call heapify on the reduced heap
            heapify(array, i, 0, comparator);
        }
    }

    private void heapify(T[] array, int n, int i, BiFunction<T, T, Integer> comparator) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // See if left child of root exists and is greater than root
        if (left < n && comparator.apply(array[left], array[largest]) > 0) {
            largest = left;
        }

        // See if right child of root exists and is greater than root
        if (right < n && comparator.apply(array[right], array[largest]) > 0) {
            largest = right;
        }

        // Change root if needed
        if (largest != i) {
            T swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(array, n, largest, comparator);
        }
    }

    @Override
    public String getComplexity() {
        return "O(n log n)";
    }
}
