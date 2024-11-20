package com.B3.business_layer.Business.Repositories.Array;

import com.B3.business_layer.Business.Help.HelpComparator;
import com.B3.domain_layer.DomainArray.Repositories.IQuickSortArrayRepository;

import java.util.Comparator;

public class QuickSortArrayRepository<T> implements IQuickSortArrayRepository<T> {

    private final HelpComparator helpComparator = new HelpComparator();

    @Override
    public T sort(T array) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (array instanceof Object[]) {
            Object[] arr = (Object[]) array;

            Comparator<Object> comparator = helpComparator.getComparator(arr[0]);

            quickSort(arr, 0, arr.length - 1, comparator);
            return (T) arr;
        } else {
            throw new IllegalArgumentException("O parâmetro fornecido não é um array do tipo Object[]");
        }
    }

    private void quickSort(Object[] arr, int low, int high, Comparator<Object> comparator) {
        if (low < high) {
            int pi = partition(arr, low, high, comparator);

            quickSort(arr, low, pi - 1, comparator);
            quickSort(arr, pi + 1, high, comparator);
        }
    }

    private int partition(Object[] arr, int low, int high, Comparator<Object> comparator) {
        Object pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparator.compare(arr[j], pivot) <= 0) {
                i++;
                Object temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Object temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
