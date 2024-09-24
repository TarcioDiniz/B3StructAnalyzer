package com.B3.struct.analyzer.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TxtHandler {

    public void createTxt(String folderPath, String fileName, String[][] data) throws IOException {
        Path directoryPath = Paths.get(folderPath);
        Path filePath = directoryPath.resolve(fileName);

        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (String[] lineArr : data) {
                for (String line : lineArr) {
                    writer.write(line);
                }
                writer.newLine();
            }
        }
    }

    public String[] readTxt(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        int lineCount = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        }

        String[] data = new String[lineCount];
        int index = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                data[index++] = line;
            }
        }
        return data;
    }
}
