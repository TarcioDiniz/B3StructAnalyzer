package com.B3.business_layer.Business.Repositories.Queue;

import com.B3.domain_layer.DomainQueue.Repositories.IQueueRepository;
import com.B3.business_layer.Business.Help.HelpComparator;

import java.util.Comparator;

public class QueueRepository<T> implements IQueueRepository<T> {

    private final HelpComparator helpComparator = new HelpComparator();

    @Override
    public T sort(T array, Comparator<Object> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (!(array instanceof Object[])) {
            throw new IllegalArgumentException("O parâmetro fornecido não é um array");
        }

        Object[] arr = (Object[]) array;

        if (arr.length <= 1) {
            return array; // Não há necessidade de ordenar se tiver 1 ou nenhum elemento
        }

        // Utilizando o HelpComparator para obter o comparador do primeiro elemento


        // Implementação do Bubble Sort
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    // Troca de elementos
                    Object temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return (T) arr;
    }
}
