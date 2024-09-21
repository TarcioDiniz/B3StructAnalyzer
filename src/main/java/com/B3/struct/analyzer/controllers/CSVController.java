package com.B3.struct.analyzer.controllers;

import com.B3.struct.analyzer.Enums.AlgorithmsType;
import com.B3.struct.analyzer.utils.CSVHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;

public class CSVController {
    private static final Array<String[]> sorter = new Array<>();

    public static void createCSVTicker(String[][] stockData, String fileName, AlgorithmsType algorithmsType) throws IOException {
        // Comparador para ordenar pelo ticker (índice 1)
        BiFunction<String[], String[], Integer> tickerComparator = (a, b) -> a[1].compareTo(b[1]);

        // Ordena os dados pelo ticker
        sorter.sorting(algorithmsType, stockData, tickerComparator);

        // Cria a lista de dados transformados com o cabeçalho
        ArrayList<String[]> transformedData = new ArrayList<>(Arrays.asList(stockData));
        transformedData.add(0, new String[]{"datetime", "ticker", "open", "close", "high", "low", "volume"});

        // Cria o CSV
        CSVHandler.createCSV("src/main/resources/" + algorithmsType.toString() + "/ticker", fileName + ".csv", transformedData);
    }

    public static void createCSVVolume(String[][] stockData, String fileName, AlgorithmsType algorithmsType) throws IOException {
        // Comparador para ordenar pelo volume (índice 6)
        BiFunction<String[], String[], Integer> volumeComparator = (a, b) -> {
            double volumeA = Double.parseDouble(a[6]);
            double volumeB = Double.parseDouble(b[6]);
            return Double.compare(volumeA, volumeB);
        };

        // Ordena os dados pelo volume
        sorter.sorting(algorithmsType, stockData, volumeComparator);

        // Cria a lista de dados transformados com o cabeçalho
        ArrayList<String[]> transformedData = new ArrayList<>(Arrays.asList(stockData));
        transformedData.add(0, new String[]{"datetime", "ticker", "open", "close", "high", "low", "volume"});

        // Cria o CSV
        CSVHandler.createCSV("src/main/resources/" + algorithmsType.toString() + "/volume", fileName + ".csv", transformedData);
    }

    public static void createCSVFluctuations(String[][] stockData, String fileName, AlgorithmsType algorithmsType) throws IOException {
        // Comparador para ordenar pela variação diária (High - Low)
        BiFunction<String[], String[], Integer> variationComparator = (a, b) -> {
            double variationA = Double.parseDouble(a[4]) - Double.parseDouble(a[5]);
            double variationB = Double.parseDouble(b[4]) - Double.parseDouble(b[5]);
            return Double.compare(variationB, variationA); // Ordem decrescente
        };

        // Ordena os dados pela variação
        sorter.sorting(algorithmsType, stockData, variationComparator);

        // Cria a lista de dados transformados com o cabeçalho
        ArrayList<String[]> transformedData = new ArrayList<>(Arrays.asList(stockData));
        transformedData.add(0, new String[]{"datetime", "ticker", "open", "close", "high", "low", "volume"});

        // Cria o CSV
        CSVHandler.createCSV("src/main/resources/" + algorithmsType.toString() + "/fluctuations", fileName + ".csv", transformedData);
    }
}
