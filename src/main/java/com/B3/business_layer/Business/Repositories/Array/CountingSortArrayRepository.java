package com.B3.business_layer.Business.Repositories.Array;

import com.B3.domain_layer.DomainArray.Repositories.ICountingSortArrayRepository;

import java.util.Comparator;

public class CountingSortArrayRepository<T> implements ICountingSortArrayRepository<T> {

    @Override
    public T sort(T array, Comparator<Object> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (array instanceof Integer[]) {
            Integer[] arr = (Integer[]) array;

            int max = arr[0];
            int min = arr[0];
            for (int num : arr) {
                if (num > max) {
                    max = num;
                }
                if (num < min) {
                    min = num;
                }
            }

            int range = max - min + 1;
            int[] count = new int[range];

            for (int num : arr) {
                count[num - min]++;
            }

            int index = 0;
            for (int i = 0; i < count.length; i++) {
                while (count[i] > 0) {
                    arr[index++] = i + min;
                    count[i]--;
                }
            }

            return (T) arr;
        } else {
            throw new IllegalArgumentException("O parâmetro fornecido não é um array do tipo Integer[]");
        }
    }
}
