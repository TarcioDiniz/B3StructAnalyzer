package com.B3.business_layer.Business.Repositories.Tree;

import com.B3.business_layer.Business.Help.HelpComparator;
import com.B3.domain_layer.DomainTree.Repositories.ITreeRepository;

import java.util.Comparator;

public class TreeRepository<T> implements ITreeRepository<T> {

    private final HelpComparator helpComparator = new HelpComparator();

    @Override
    public T sort(T array) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (array instanceof Object[] && ((Object[]) array).length <= 1) {
            return array;
        }

        if (array instanceof Object[]) {
            Object[] arr = (Object[]) array;

            Comparator<Object> comparator = helpComparator.getComparator(arr[0]);

            boolean swapped;
            int n = arr.length;

            do {
                swapped = false;
                for (int i = 0; i < n - 1; i++) {
                    if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                        Object temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        swapped = true;
                    }
                }
                n--;
            } while (swapped);

            return (T) arr;
        } else {
            throw new IllegalArgumentException("O parâmetro fornecido não é um array");
        }
    }
}
