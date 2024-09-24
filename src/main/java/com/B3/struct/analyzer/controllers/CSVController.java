package com.B3.struct.analyzer.controllers;

import com.B3.struct.analyzer.Enums.AlgorithmsType;
import com.B3.struct.analyzer.utils.CSVHandler;

import java.io.IOException;
import java.util.function.BiFunction;

public class CSVController {
    private static final Array<String[]> sorter = new Array<>();

    public static void createCSVTicker(String[][] stockData, String fileName, AlgorithmsType algorithmsType) throws IOException {
        // Comparador para ordenar pelo ticker (índice 1)
        BiFunction<String[], String[], Integer> tickerComparator = (a, b) -> a[1].compareTo(b[1]);

        sorter.sorting(algorithmsType, stockData, tickerComparator);

        String[][] transformedData = new String[stockData.length + 1][];
        transformedData[0] = new String[]{"datetime", "ticker", "open", "close", "high", "low", "volume"};
        System.arraycopy(stockData, 0, transformedData, 1, stockData.length);

        CSVHandler.createCSV("src/main/resources/" + algorithmsType.toString() + "/ticker", fileName + ".csv", transformedData);
    }

    public static void createCSVVolume(String[][] stockData, String fileName, AlgorithmsType algorithmsType) throws IOException {
        // Comparador para ordenar pelo volume (índice 6)
        BiFunction<String[], String[], Integer> volumeComparator = (a, b) -> {
            double volumeA = Double.parseDouble(a[6]);
            double volumeB = Double.parseDouble(b[6]);
            return Double.compare(volumeA, volumeB);
        };

        sorter.sorting(algorithmsType, stockData, volumeComparator);

        String[][] transformedData = new String[stockData.length + 1][];
        transformedData[0] = new String[]{"datetime", "ticker", "open", "close", "high", "low", "volume"};
        System.arraycopy(stockData, 0, transformedData, 1, stockData.length);

        CSVHandler.createCSV("src/main/resources/" + algorithmsType.toString() + "/volume", fileName + ".csv", transformedData);
    }

    public static void createCSVFluctuations(String[][] stockData, String fileName, AlgorithmsType algorithmsType) throws IOException {
        // Comparador para ordenar pela variação diária (High - Low)
        BiFunction<String[], String[], Integer> variationComparator = (a, b) -> {
            double variationA = Double.parseDouble(a[4]) - Double.parseDouble(a[5]);
            double variationB = Double.parseDouble(b[4]) - Double.parseDouble(b[5]);
            return Double.compare(variationB, variationA); // Ordem decrescente
        };

        sorter.sorting(algorithmsType, stockData, variationComparator);

        String[][] transformedData = new String[stockData.length + 1][];
        transformedData[0] = new String[]{"datetime", "ticker", "open", "close", "high", "low", "volume"};
        System.arraycopy(stockData, 0, transformedData, 1, stockData.length);

        // Cria o CSV
        CSVHandler.createCSV("src/main/resources/" + algorithmsType.toString() + "/fluctuations", fileName + ".csv", transformedData);
    }
}
