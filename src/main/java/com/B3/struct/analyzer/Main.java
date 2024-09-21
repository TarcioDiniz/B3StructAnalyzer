package com.B3.struct.analyzer;

import com.B3.struct.analyzer.Enums.AlgorithmsType;
import com.B3.struct.analyzer.utils.SystemInfoCollector;
import com.B3.struct.analyzer.utils.TxtHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        SystemInfoCollector systemInfoCollector = new SystemInfoCollector();
        String modelInfo = systemInfoCollector.getModel();
        String cpuInfo = systemInfoCollector.getCpuInfo();
        String gpuInfo = systemInfoCollector.getGpuInfo();
        String ramInfo = systemInfoCollector.getRamInfo();
        String memoryUsage = systemInfoCollector.getMemoryUsage();

        TxtHandler txtHandler = new TxtHandler();
        AlgorithmsType[] algorithmsTypes = AlgorithmsType.values();

        for (AlgorithmsType algorithmsType : algorithmsTypes) {
            List<Long> executionTimes = new ArrayList<>();
            Map<Long, String> referenceExecution = new HashMap<>();

            Path folderPath = Paths.get("src/main/resources", algorithmsType.name());
            try {
                Files.createDirectories(folderPath);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            String fileName = algorithmsType.name() + "_execution_times.txt";

            // Adicionar lista para armazenar o uso de memória
            List<Long> memoryUsages = new ArrayList<>();
            List<Object> objectList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {  // Aumente o número de iterações para coletar mais dados
                long startTime = System.nanoTime();

                // Captura o uso de memória antes da execução
                long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                try {
                    // Simular a execução do algoritmo aqui (substitua pelo seu código real)
                    // Exemplo: executeAlgorithm(algorithmsType);
                    for (int j = 0; j < 1_000_000; j++) {
                        objectList.add(new Object());
                    }

                    long endTime = System.nanoTime();
                    long executionTime = (endTime - startTime) / 1_000_000; // Converter para ms

                    // Captura o uso de memória após a execução
                    long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                    // Adiciona a execução e o uso de memória
                    executionTimes.add(executionTime);
                    referenceExecution.put(executionTime, String.format("Execução %d, %s, %d", (i + 1), algorithmsType.name(), executionTime));

                    // Adiciona o uso de memória
                    memoryUsages.add(memoryAfter - memoryBefore);
                } catch (Exception e) {
                    long endTime = System.nanoTime();
                    long executionTime = (endTime - startTime) / 1_000_000; // Converter para ms

                    // Capture o uso de memória no momento da exceção
                    long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    memoryUsages.add(memoryAfter - memoryBefore); // Adiciona o uso de memória na exceção

                    // Registra a exceção e o tempo de execução
                    System.err.printf("Erro na execução %d: %s (Tempo: %d ms)%n", (i + 1), e.getMessage(), executionTime);
                }
            }



            executionTimes.sort(Long::compareTo);

            // Calcular a média do uso de memória
            long totalMemoryUsage = 0;
            for (long memoryUsageValue : memoryUsages) {
                totalMemoryUsage += memoryUsageValue;
            }
            long averageMemoryUsage = Math.abs(totalMemoryUsage / memoryUsages.size());

            List<String> dataToWrite = new ArrayList<>();
            dataToWrite.add("MODEL: " + modelInfo);
            dataToWrite.add("CPU: " + cpuInfo);
            dataToWrite.add("GPU: " + gpuInfo);
            dataToWrite.add("RAM: " + ramInfo);
            dataToWrite.add("");
            dataToWrite.add("Uso de Memória: " + memoryUsage);
            dataToWrite.add("Uso Médio de Memória durante execução: " + averageMemoryUsage + " bytes");
            dataToWrite.add("");
            dataToWrite.add("Classificação, Execução, Algoritmo, Tempo (ms)");

            // Adicionando as classificações
            dataToWrite.add("Melhor, " + referenceExecution.get(executionTimes.get(0)));
            dataToWrite.add("Médio, " + referenceExecution.get(executionTimes.get(1)));
            dataToWrite.add("Pior, " + referenceExecution.get(executionTimes.get(2)));

            // Escrevendo em um arquivo de texto
            try {
                txtHandler.createTxt(folderPath.toString(), fileName, dataToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
