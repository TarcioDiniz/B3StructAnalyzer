package com.B3.business_layer.Business.Repositories.Array;

import com.B3.business_layer.Business.Help.HelpComparator;
import com.B3.domain_layer.DomainArray.Repositories.IMergeSortArrayRepository;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSortArrayRepository<T> implements IMergeSortArrayRepository<T> {

    private final HelpComparator helpComparator = new HelpComparator();

    @Override
    public T sort(T array, Comparator<Object> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (array instanceof Object[]) {
            Object[] arr = (Object[]) array;

            mergeSort(arr, comparator);
            return (T) arr;
        } else {
            throw new IllegalArgumentException("O parâmetro fornecido não é um array do tipo Object[]");
        }
    }

    private void mergeSort(Object[] arr, Comparator<Object> comparator) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;
        Object[] left = Arrays.copyOfRange(arr, 0, mid);
        Object[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        merge(arr, left, right, comparator);
    }

    private void merge(Object[] arr, Object[] left, Object[] right, Comparator<Object> comparator) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
