package com.B3.struct.analyzer.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVHandler {

    public static void createCSV(String folderPath, String fileName, String[][] data) throws IOException {
        Path directoryPath = Paths.get(folderPath);
        Path filePath = directoryPath.resolve(fileName);

        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }

    public static String[][] readCSV(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        // First, count the number of lines to create an array with the correct size
        int lineCount = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        }

        // Initialize the array
        String[][] data = new String[lineCount][];
        int index = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data[index++] = values;
            }
        }
        return data;
    }
}
