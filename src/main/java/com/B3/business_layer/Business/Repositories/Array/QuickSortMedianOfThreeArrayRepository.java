package com.B3.business_layer.Business.Repositories.Array;

import com.B3.business_layer.Business.Help.HelpComparator;
import com.B3.domain_layer.DomainArray.Repositories.IQuickSortMedianOfThreeArrayRepository;

import java.util.Comparator;

public class QuickSortMedianOfThreeArrayRepository<T> implements IQuickSortMedianOfThreeArrayRepository<T> {

    private final HelpComparator helpComparator = new HelpComparator();

    @Override
    public T sort(T array, Comparator<Object> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (array instanceof Object[]) {
            Object[] arr = (Object[]) array;

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
        int pivotIndex = medianOfThree(arr, low, high, comparator);
        Object pivot = arr[pivotIndex];

        swap(arr, pivotIndex, high);

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    private int medianOfThree(Object[] arr, int low, int high, Comparator<Object> comparator) {
        int mid = low + (high - low) / 2;

        if (comparator.compare(arr[low], arr[mid]) > 0) {
            swap(arr, low, mid);
        }
        if (comparator.compare(arr[low], arr[high]) > 0) {
            swap(arr, low, high);
        }
        if (comparator.compare(arr[mid], arr[high]) > 0) {
            swap(arr, mid, high);
        }

        return mid;
    }

    private void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
