package com.B3.struct.analyzer.controllers;

import com.B3.struct.analyzer.Enums.AlgorithmsType;
import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.services.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Array<T> {
    private static final Map<AlgorithmsType, Algorithms<?>> mapAlgorithms;

    static {
        mapAlgorithms = new HashMap<AlgorithmsType, Algorithms<?>>();
        mapAlgorithms.put(AlgorithmsType.INSERTION_SORT, new InsertionSort<>());
        mapAlgorithms.put(AlgorithmsType.SELECTION_SORT, new SelectionSort<>());
        mapAlgorithms.put(AlgorithmsType.BUBBLE_SORT, new BubbleSort<>());
        mapAlgorithms.put(AlgorithmsType.MERGE_SORT, new MergeSort<>());
        mapAlgorithms.put(AlgorithmsType.QUICK_SORT, new QuickSort<>());
        mapAlgorithms.put(AlgorithmsType.QUICK_SORT_MEDIAN_OF_THREE, new QuickSortMedianOfThree<>());
        mapAlgorithms.put(AlgorithmsType.HEAP_SORT, new HeapSort<>());
        mapAlgorithms.put(AlgorithmsType.RADIX_SORT, new RadixSort());
    }

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

    @SuppressWarnings("unchecked")
    public void sorting(AlgorithmsType type, T[] array, BiFunction<T, T, Integer> comparator) {
        Algorithms<T> algorithm = (Algorithms<T>) mapAlgorithms.get(type);
        algorithm.sort(array, comparator);
    }

    @SuppressWarnings("unchecked")
    public void sorting(AlgorithmsType type, T[][] array, BiFunction<T[], T[], Integer> comparator) {
        Algorithms<T[]> algorithm = (Algorithms<T[]>) mapAlgorithms.get(type);
        algorithm.sort(array, comparator);
    }

    public String getName(AlgorithmsType type) {
        return mapAlgorithms.get(type).getName();
    }
}
