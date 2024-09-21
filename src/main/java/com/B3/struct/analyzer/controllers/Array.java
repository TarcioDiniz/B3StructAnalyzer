package com.B3.struct.analyzer.controllers;

import com.B3.struct.analyzer.Enums.AlgorithmsType;
import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.services.*;

import java.util.HashMap;
import java.util.Map;

public class Array<T> {
    private static final Map<AlgorithmsType, Algorithms<?>> mapAlgorithms = new HashMap<AlgorithmsType, Algorithms<?>>() {
        {
            put(AlgorithmsType.INSERTION_SORT, new InsertionSort<>());
            put(AlgorithmsType.SELECTION_SORT, new SelectionSort<>());
            put(AlgorithmsType.BUBBLE_SORT, new BubbleSort<>());
            put(AlgorithmsType.MERGE_SORT, new MergeSort<>());
            put(AlgorithmsType.QUICK_SORT, new QuickSort<>());
            put(AlgorithmsType.QUICK_SORT_MEDIAN_OF_THREE, new QuickSortMedianOfThree<>());
            put(AlgorithmsType.HEAP_SORT, new HeapSort<>());
            put(AlgorithmsType.RADIX_SORT, new RadixSort());
        }
    };

    @SuppressWarnings("unchecked")
    public void sorting(AlgorithmsType type, T[] array) {
        Algorithms<T> algorithm = (Algorithms<T>) mapAlgorithms.get(type);
        algorithm.sort(array);
    }

    @SuppressWarnings("unchecked")
    public void sorting(T[] array) {
        Algorithms<T> algorithm = (Algorithms<T>) mapAlgorithms.get(AlgorithmsType.MERGE_SORT);
        algorithm.sort(array);
    }
}
