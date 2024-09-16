package com.B3.struct.analyzer;

import com.B3.struct.analyzer.Enums.AlgorithmsType;
import com.B3.struct.analyzer.controllers.Array;

public class Main {
    public static void main(String[] args) {
        // Teste com Integer (comparador implícito)
        Array<Integer> bubbleSortInt = new Array<>();
        Integer[] intArray = {5, 2, 9, 1, 5, 6};

        System.out.println("Array de Inteiros Antes:");
        printArray(intArray);

        bubbleSortInt.sorting(AlgorithmsType.BUBBLE_SORT, intArray);

        System.out.println("Array de Inteiros Depois (Ordenado):");
        printArray(intArray);
        System.out.println();

    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
