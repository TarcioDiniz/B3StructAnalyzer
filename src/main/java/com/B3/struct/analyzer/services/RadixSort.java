package com.B3.struct.analyzer.services;

import com.B3.struct.analyzer.interfaces.Algorithms;
import com.B3.struct.analyzer.utils.ComparatorProvider;

import java.util.Arrays;
import java.util.function.BiFunction;

public class RadixSort implements Algorithms<Integer> {

    @Override
    public String getName() {
        return "Radix Sort";
    }

    @Override
    public void sort(Integer[] array) {
        sort(array, ComparatorProvider.getDefaultComparator(array));
    }

    @Override
    public void sort(Integer[] array, BiFunction<Integer, Integer, Integer> comparator) {
        int max = Arrays.stream(array).max(Integer::compare).orElse(0);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    private void countingSort(Integer[] array, int exp) {
        int n = array.length;
        Integer[] output = new Integer[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (Integer num : array) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    @Override
    public String getComplexity() {
        return "O(n * k)";
    }
}
