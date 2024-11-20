package com.B3.business_layer.Business.Repositories.Array;

import com.B3.business_layer.Business.Help.HelpComparator;
import com.B3.domain_layer.DomainArray.Repositories.IHeapSortArrayRepository;

import java.util.Comparator;

public class HeapSortArrayRepository<T> implements IHeapSortArrayRepository<T> {

    private final HelpComparator helpComparator = new HelpComparator();

    @Override
    public T sort(T array) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (array instanceof Object[]) {
            Object[] arr = (Object[]) array;
            int n = arr.length;

            Comparator<Object> comparator = helpComparator.getComparator(arr[0]);

            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i, comparator);
            }

            for (int i = n - 1; i > 0; i--) {
                Object temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                heapify(arr, i, 0, comparator);
            }

            return (T) arr;
        } else {
            throw new IllegalArgumentException("O parâmetro fornecido não é um array do tipo Object[]");
        }
    }

    private void heapify(Object[] arr, int n, int i, Comparator<Object> comparator) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparator.compare(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && comparator.compare(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            Object swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest, comparator);
        }
    }
}
