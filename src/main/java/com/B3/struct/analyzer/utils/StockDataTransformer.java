package com.B3.struct.analyzer.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockDataTransformer {

    public static String[][] transformDateFormat(String inputFilePath, String outputFilePath) throws IOException {
        String[][] data = CSVHandler.readCSV(inputFilePath).toArray(new String[0][]);

        // Create a list to store transformed data, skipping the header
        List<String[]> transformedData = new ArrayList<>();

        for (int i = 1; i < data.length; i++) { // Start from index 1 to skip the header
            String[] row = data[i];
            String date = row[0];
            String transformedDate = transformDate(date);
            row[0] = transformedDate;
            transformedData.add(row);
        }

        // Convert the List<String[]> back to String[][]
        String[][] result = new String[transformedData.size()][];
        result = transformedData.toArray(result);

        transformedData.add(0, new String[]{"datetime", "ticker", "open", "close", "high", "low", "volume"});

        CSVHandler.createCSV(outputFilePath, "b3stocks_T1.csv", transformedData);

        return result;
    }


    private static String transformDate(String date) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = inputFormat.parse(date);
            return outputFormat.format(parsedDate);
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + date);
            return date;
        }
    }

    public static void filterAndCreateCSV(String[][] stockData, String fileName) throws IOException {
        Map<String, String[]> filteredData = new HashMap<>();

        // Filtra os dados para manter apenas um registro por dia com o maior volume
        for (String[] record : stockData) {
            String date = record[0];
            double volume = Double.parseDouble(record[6]);

            if (!filteredData.containsKey(date) || volume > Double.parseDouble(filteredData.get(date)[6])) {
                filteredData.put(date, record);
            }
        }

        // Converte o mapa de volta para um array para criar o CSV
        String[][] result = new String[filteredData.size()][];
        int index = 0;
        for (String[] record : filteredData.values()) {
            result[index++] = record;
        }

        // Adiciona o cabeçalho
        String[] header = {"datetime", "ticker", "open", "close", "high", "low", "volume"};
        String[][] finalData = new String[result.length + 1][];
        finalData[0] = header;
        System.arraycopy(result, 0, finalData, 1, result.length);

        // Cria o CSV
        CSVHandler.createCSV("src/main/resources/", fileName, new ArrayList<>(Arrays.asList(finalData)));
    }
}
