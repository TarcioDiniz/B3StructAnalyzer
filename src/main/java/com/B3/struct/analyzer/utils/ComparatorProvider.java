package com.B3.struct.analyzer.utils;

import java.util.function.BiFunction;

public class ComparatorProvider {

    public static <T> BiFunction<T, T, Integer>  getDefaultComparator(T[] array) {
        if (array != null && array.length > 0) {
            if (array[0] instanceof Integer) {
                return (a, b) -> ((Integer) a).compareTo((Integer) b);
            } else if (array[0] instanceof String) {
                return (a, b) -> ((String) a).compareTo((String) b);
            } else if (array[0] instanceof Double) {
                return (a, b) -> ((Double) a).compareTo((Double) b);
            } else if (array[0] instanceof Float) {
                return (a, b) -> ((Float) a).compareTo((Float) b);
            } else if (array[0] instanceof Long) {
                return (a, b) -> ((Long) a).compareTo((Long) b);
            }
        }
        throw new IllegalArgumentException("No default comparator available for the given type.");
    }
}
