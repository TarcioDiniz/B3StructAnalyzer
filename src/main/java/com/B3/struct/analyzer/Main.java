package com.B3.struct.analyzer;

import com.B3.struct.analyzer.Enums.AlgorithmsType;
import com.B3.struct.analyzer.controllers.Array;
import com.B3.struct.analyzer.controllers.CSVController;
import com.B3.struct.analyzer.services.MergeSort;
import com.B3.struct.analyzer.utils.Convert;
import com.B3.struct.analyzer.utils.StockDataTransformer;
import com.B3.struct.analyzer.utils.SystemInfoCollector;
import com.B3.struct.analyzer.utils.TxtHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Main {

    public static void main(String[] args) throws IOException {

        Map<String, BiConsumer<String, AlgorithmsType>> functions = new HashMap<>();

        AlgorithmsType[] algorithmsTypes = new AlgorithmsType[]{
//                AlgorithmsType.INSERTION_SORT,
//                AlgorithmsType.SELECTION_SORT,
//                AlgorithmsType.QUICK_SORT,
//                AlgorithmsType.QUICK_SORT_MEDIAN_OF_THREE,
                AlgorithmsType.MERGE_SORT,
                AlgorithmsType.HEAP_SORT,
//                AlgorithmsType.RADIX_SORT,
        };

        // Supondo que transformedData seja um array de duas dimensões String[][]
        String[][] transformedData = StockDataTransformer.transformDateFormat(
                "src/main/resources/b3_stocks_1994_2020.csv",
                "src/main/resources"
        );

        if (transformedData.length > 1) {
            String[][] dataWithoutHeader = new String[transformedData.length - 1][];
            System.arraycopy(transformedData, 1, dataWithoutHeader, 0, transformedData.length - 1);
            transformedData = dataWithoutHeader;
        }


        StockDataTransformer.filterAndCreateCSV(transformedData, "b3stocks_F1");

        String[][] finalTransformedData = transformedData;


        functions.put("b3stocks_ticker", (fileName, algorithmsType) ->
                {
                    try {
                        CSVController.createCSVTicker(
                                finalTransformedData,
                                "b3stocks_ticker_" + fileName, algorithmsType);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        functions.put("b3stocks_volume", (fileName, algorithmsType) ->
                {
                    try {
                        CSVController.createCSVVolume(
                                finalTransformedData,
                                "b3stocks_volume_" + fileName, algorithmsType);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        functions.put("b3stocks_fluctuations", (fileName, algorithmsType) ->
                {
                    try {
                        CSVController.createCSVFluctuations(
                                finalTransformedData,
                                "b3stocks_fluctuations_" + fileName, algorithmsType);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        executeAlgorithm(algorithmsTypes, functions);
    }


    private static void executeAlgorithm(
            AlgorithmsType[] algorithmsTypes, Map<String, BiConsumer<String, AlgorithmsType>> functions) {
        SystemInfoCollector systemInfoCollector = new SystemInfoCollector();
        String modelInfo = systemInfoCollector.getModel();
        String cpuInfo = systemInfoCollector.getCpuInfo();
        String gpuInfo = systemInfoCollector.getGpuInfo();
        String ramInfo = systemInfoCollector.getRamInfo();
        String operatingSystem = systemInfoCollector.getOperatingSystem();
        String memoryUsage = systemInfoCollector.getMemoryUsage();

        System.out.println("Iniciando o processamento dos dados...");

        TxtHandler txtHandler = new TxtHandler();

        for (Map.Entry<String, BiConsumer<String, AlgorithmsType>> entry : functions.entrySet()) {
            System.out.println();
            String functionName = entry.getKey();
            BiConsumer<String, AlgorithmsType> function = entry.getValue();

            for (AlgorithmsType algorithmsType : algorithmsTypes) {

                System.out.println("Executando: " + functionName + " - " + algorithmsType.toString());

                long[] executionTimes = new long[3];
                String[] referenceExecution = new String[3];

                Path folderPath = Paths.get("src/main/resources", algorithmsType.name());
                try {
                    Files.createDirectories(folderPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                Array<Integer> array = new Array<>();

                String fileName = functionName + "_" + array.getName(algorithmsType) + "_analyzer.txt";

                long[] memoryUsages = new long[3]; // For storing memory usage
                for (int i = 0; i < 3; i++) {
                    long startTime = System.nanoTime();

                    long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                    try {
                        String classification = (i == 0) ? "melhorCaso" : (i == 1) ? "medioCaso" : "piorCaso";

                        function.accept(array.getName(algorithmsType) + "_" + classification, algorithmsType);

                        long endTime = System.nanoTime();
                        long executionTime = (endTime - startTime) / 1_000_000; // Converter para ms

                        long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                        executionTimes[i] = executionTime;
                        referenceExecution[i] = String.format("Execução %d, %s, %d", (i + 1), algorithmsType.name(), executionTime);

                        memoryUsages[i] = memoryAfter - memoryBefore;
                    } catch (Exception e) {
                        long endTime = System.nanoTime();
                        long executionTime = (endTime - startTime) / 1_000_000; // Converter para ms

                        long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                        memoryUsages[i] = memoryAfter - memoryBefore;

                        System.err.printf("Erro na execução %d: %s (Tempo: %d ms)%n", (i + 1), e.getMessage(), executionTime);
                    }
                }

                // Sorting execution times
                MergeSort<Long> mergeSort = new MergeSort<>();
                Long[] executionTimesObj = Convert.convertLongArrayToLongObjectArray(executionTimes);
                mergeSort.sort(executionTimesObj);

                // Calcular a média do uso de memória
                long totalMemoryUsage = 0;
                for (long memoryUsageValue : memoryUsages) {
                    totalMemoryUsage += memoryUsageValue;
                }
                long averageMemoryUsage = totalMemoryUsage / memoryUsages.length;

                String[][] dataToWrite = {
                        {"MODEL: " + modelInfo},
                        {"CPU: " + cpuInfo},
                        {"GPU: " + gpuInfo},
                        {"RAM: " + ramInfo},
                        {"OPERATING_SYSTEM: " + operatingSystem},
                        {""},  // Linha em branco
                        {"Uso de Memória: " + memoryUsage},
                        {"Uso Médio de Memória durante execução: " + averageMemoryUsage + " bytes"},
                        {""},  // Outra linha em branco
                        {"Classificação, Execução, Algoritmo, Tempo (ms)"},
                        {"Melhor, " + referenceExecution[0]},
                        {"Médio, " + referenceExecution[1]},
                        {"Pior, " + referenceExecution[2]}
                };

                // Escrevendo em um arquivo de texto
                try {
                    txtHandler.createTxt(folderPath.toString(), fileName, dataToWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\nExecução concluída.");
    }


}
