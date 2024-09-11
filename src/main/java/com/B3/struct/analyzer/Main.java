package com.B3.struct.analyzer;

import com.B3.struct.analyzer.services.BubbleSort;

import java.util.Arrays;
import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Integer[] intArray = {5, 3, 8, 1, 2};
        BubbleSort<Integer> bubbleSortInt = new BubbleSort<>();
        System.out.println("Array de inteiros antes da ordenação: " + Arrays.toString(intArray));

        // Ordenar com base na ordem natural
        bubbleSortInt.sort(intArray, Comparator.naturalOrder());
        System.out.println("Array de inteiros depois da ordenação: " + Arrays.toString(intArray));
        System.out.println("Complexidade: " + bubbleSortInt.getComplexity());

        // Exemplo de uso com strings
        String[] strArray = {"banana", "apple", "cherry", "date"};
        BubbleSort<String> bubbleSortStr = new BubbleSort<>();
        System.out.println("\nArray de strings antes da ordenação: " + Arrays.toString(strArray));

        // Ordenar com base na ordem natural
        bubbleSortStr.sort(strArray, Comparator.naturalOrder());
        System.out.println("Array de strings depois da ordenação: " + Arrays.toString(strArray));
        System.out.println("Complexidade: " + bubbleSortStr.getComplexity());
    }
}