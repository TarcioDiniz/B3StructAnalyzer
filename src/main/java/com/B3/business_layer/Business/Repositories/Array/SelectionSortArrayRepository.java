package com.B3.business_layer.Business.Repositories.Array;

import com.B3.business_layer.Business.Help.HelpComparator;
import com.B3.domain_layer.DomainArray.Repositories.ISelectionSortArrayRepository;

import java.util.Comparator;

public class SelectionSortArrayRepository<T> implements ISelectionSortArrayRepository<T> {

    private final HelpComparator helpComparator = new HelpComparator();

    @Override
    public T sort(T array, Comparator<Object> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (array instanceof Object[]) {
            Object[] arr = (Object[]) array;

            selectionSort(arr, comparator);
            return (T) arr;
        } else {
            throw new IllegalArgumentException("O parâmetro fornecido não é um array do tipo Object[]");
        }
    }

    private void selectionSort(Object[] arr, Comparator<Object> comparator) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    private void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
