package com.B3.business_layer.Business.Repositories.Hash;

import com.B3.business_layer.Business.Help.HelpComparator;
import com.B3.domain_layer.DomainHash.Repositories.IHashRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HashRepository<T> implements IHashRepository<T> {

    private final HelpComparator helpComparator = new HelpComparator();

    @Override
    public T sort(T array, Comparator<Object> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("O array não pode ser nulo");
        }

        if (array instanceof Object[] && ((Object[]) array).length <= 1) {
            return array;
        }

        if (array instanceof Object[]) {
            Object[] arr = (Object[]) array;


            Map<Integer, Object[]> hashTable = new HashMap<>();

            for (Object element : arr) {
                int hashKey = element.hashCode();

                if (!hashTable.containsKey(hashKey)) {
                    hashTable.put(hashKey, new Object[]{element});
                } else {
                    Object[] bucket = hashTable.get(hashKey);
                    Object[] newBucket = new Object[bucket.length + 1];
                    System.arraycopy(bucket, 0, newBucket, 0, bucket.length);
                    newBucket[bucket.length] = element;
                    hashTable.put(hashKey, newBucket);
                }
            }

            Object[] sortedArray = new Object[arr.length];
            int index = 0;

            for (Map.Entry<Integer, Object[]> entry : hashTable.entrySet()) {
                Object[] bucket = entry.getValue();
                for (Object obj : bucket) {
                    sortedArray[index++] = obj;
                }
            }

            sortArray(sortedArray, comparator);

            System.arraycopy(sortedArray, 0, arr, 0, arr.length);

            return (T) arr;
        } else {
            throw new IllegalArgumentException("O parâmetro fornecido não é um array");
        }
    }

    private void sortArray(Object[] array, Comparator<Object> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[i], array[j]) > 0) {
                    Object temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
