package com.B3.struct.analyzer.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

    public static void createCSV(String folderPath, String fileName, List<String[]> data) throws IOException {
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

    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        Path path = Paths.get(filePath);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        }
        return data;
    }
}
