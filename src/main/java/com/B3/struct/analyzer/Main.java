package com.B3.struct.analyzer;

import com.B3.struct.analyzer.services.BubbleSort;

public class Main {
    public static void main(String[] args) {
        // Teste com Integer (comparador implícito)
        BubbleSort<Integer> bubbleSortInt = new BubbleSort<>();
        Integer[] intArray = {5, 2, 9, 1, 5, 6};

        System.out.println("Array de Inteiros Antes:");
        printArray(intArray);

        // Chama o método sem passar um comparador - usa o padrão
        bubbleSortInt.sort(intArray);

        System.out.println("Array de Inteiros Depois (Ordenado):");
        printArray(intArray);
        System.out.println();

        // Teste com String (comparador implícito)
        BubbleSort<String> bubbleSortString = new BubbleSort<>();
        String[] stringArray = {"apple", "orange", "banana", "grape"};

        System.out.println("Array de Strings Antes:");
        printArray(stringArray);

        // Chama o método sem passar um comparador - usa o padrão
        bubbleSortString.sort(stringArray);

        System.out.println("Array de Strings Depois (Ordenado):");
        printArray(stringArray);
        System.out.println();

        // Teste com String (comparador customizado - ordem reversa)
        System.out.println("Array de Strings Antes (Ordem Reversa):");
        printArray(stringArray);

        // Passa um comparador customizado para ordenar em ordem reversa
        bubbleSortString.sort(stringArray, (a, b) -> b.compareTo(a));

        System.out.println("Array de Strings Depois (Ordenado Reverso):");
        printArray(stringArray);
    }

    // Método auxiliar para imprimir o array
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
