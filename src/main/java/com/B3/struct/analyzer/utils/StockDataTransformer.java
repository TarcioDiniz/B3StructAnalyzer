package com.B3.struct.analyzer.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockDataTransformer {

    public static String[][] transformDateFormat(String inputFilePath, String outputFilePath) throws IOException {
        String[][] data = CSVHandler.readCSV(inputFilePath);

        String[][] transformedData = new String[data.length - 1][];

        for (int i = 1; i < data.length; i++) {
            String[] row = data[i];
            String date = row[0];
            String transformedDate = transformDate(date);
            row[0] = transformedDate;
            transformedData[i - 1] = row;
        }

        String[][] result = new String[transformedData.length + 1][];
        result[0] = new String[]{"datetime", "ticker", "open", "close", "high", "low", "volume"};
        System.arraycopy(transformedData, 0, result, 1, transformedData.length);

        CSVHandler.createCSV(outputFilePath, "b3stocks_T1.csv", result);

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

        // Skip the header row (first row) and filter the data
        for (int i = 1; i < stockData.length; i++) {  // Start from 1 to skip header
            String[] record = stockData[i];
            String date = record[0];
            String volumeStr = record[6];

            // Check if volumeStr can be parsed to a double
            double volume;
            try {
                volume = Double.parseDouble(volumeStr);
            } catch (NumberFormatException e) {
                System.err.println("Invalid volume value: " + volumeStr + " on date: " + date);
                continue;  // Skip this record if volume is invalid
            }

            // Keep only the record with the highest volume for each date
            if (!filteredData.containsKey(date) || volume > Double.parseDouble(filteredData.get(date)[6])) {
                filteredData.put(date, record);
            }
        }

        // Convert the map back to an array to create the CSV
        String[][] result = new String[filteredData.size()][];
        int index = 0;
        for (String[] record : filteredData.values()) {
            result[index++] = record;
        }

        String[] header = {"datetime", "ticker", "open", "close", "high", "low", "volume"};
        String[][] finalData = new String[result.length + 1][];
        finalData[0] = header;
        System.arraycopy(result, 0, finalData, 1, result.length);

        // Create the CSV
        CSVHandler.createCSV("src/main/resources/", fileName, finalData);
    }

}
