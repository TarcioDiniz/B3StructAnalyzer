package com.B3.business_layer.Business.Help;

import java.util.Comparator;

public class HelpComparator {
    public <T> Comparator<T> getComparator(T element) {
        if (element instanceof Comparable) {
            return (Comparator<T>) (o1, o2) -> ((Comparable<T>) o1).compareTo(o2);
        } else {
            throw new IllegalArgumentException("O elemento não é comparável");
        }
    }
}