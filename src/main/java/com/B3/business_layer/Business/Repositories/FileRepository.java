package com.B3.business_layer.Business.Repositories;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.CsvColumnEnum;
import com.B3.domain_layer.Domain.Repositories.IFileRepository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileRepository implements IFileRepository<String[]> {

    private final Map<String, Integer> columnIndexMap = new HashMap<>();
    private final ResultRepository resultRepository = new ResultRepository();

    @Override
    public Result writeFile(String fileName, String[] array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String header = String.join(",", java.util.Arrays.stream(CsvColumnEnum.values()).map(CsvColumnEnum::getColumnName).toArray(String[]::new));
            writer.write(header);
            writer.newLine();

            for (String line : array) {
                writer.write(line);
                writer.newLine();
            }
            return resultRepository.result("Arquivo '" + fileName + "' escrito com sucesso.", true, null);
        } catch (IOException e) {
            return resultRepository.result("Erro ao escrever o arquivo.", false, null);
        }
    }

    @Override
    public String[] readFile(String fileName) {
        String[] result = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                if (lineCount == 0) {
                    String[] headerColumns = line.split(",");
                    for (int i = 0; i < headerColumns.length; i++) {
                        String column = headerColumns[i].trim();
                        try {
                            CsvColumnEnum.fromColumnName(column);
                            columnIndexMap.put(column, i);
                        } catch (IllegalArgumentException e) {
                            System.err.println("Coluna inválida encontrada no cabeçalho: " + column);
                        }
                    }
                }
                lineCount++;
            }

            try (BufferedReader dataReader = new BufferedReader(new FileReader(fileName))) {
                result = new String[lineCount - 1];
                int index = 0;
                dataReader.readLine();
                while ((line = dataReader.readLine()) != null) {
                    result[index++] = line;
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return result;
    }

    @Override
    public String getColumnValue(String[] row, CsvColumnEnum column) {
        Integer columnIndex = columnIndexMap.get(column.getColumnName());
        if (columnIndex != null && columnIndex < row.length) {
            return row[columnIndex];
        } else {
            throw new IllegalArgumentException("Coluna não encontrada ou índice inválido.");
        }
    }
}
