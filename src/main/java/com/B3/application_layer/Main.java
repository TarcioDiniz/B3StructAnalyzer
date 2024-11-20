package com.B3.application_layer;

import com.B3.application_layer.Controllers.ArrayDateFormatterController;
import com.B3.application_layer.Controllers.SortController;
import com.B3.business_layer.Business.Services.SystemInfoCollectorService;
import com.B3.business_layer.CrossCutting.ServiceRegistration;
import com.B3.business_layer.CrossCutting.ServiceRegistry;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.Domain.Repositories.IFileRepository;
import com.B3.domain_layer.Domain.Repositories.IResultRepository;
import com.B3.domain_layer.Domain.Services.IDateFormatter;
import com.B3.domain_layer.Domain.Services.IStockFilterService;
import com.B3.domain_layer.DomainArray.Services.IArrayService;

import java.util.Comparator;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        new ServiceRegistration<String[]>().addServices();

        SystemInfoCollectorService systemInfoCollector = new SystemInfoCollectorService();

        String modelInfo = systemInfoCollector.getModel();
        String cpuInfo = systemInfoCollector.getCpuInfo();
        String gpuInfo = systemInfoCollector.getGpuInfo();
        String ramInfo = systemInfoCollector.getRamInfo();
        String operatingSystem = systemInfoCollector.getOperatingSystem();
        String memoryUsage = systemInfoCollector.getMemoryUsage();

        IResultRepository resultRepository = ServiceRegistry.getInstance().getService(IResultRepository.class);

        System.out.println();

        Result messageInitial = resultRepository.result("Inicializando aplicação.", true, null);

        System.out.println(messageInitial.message + "\n");

        System.out.println(
                "Informações do sistema: \n"
                        + "Modelo: " + modelInfo + "\n"
                        + "CPU: " + cpuInfo + "\n"
                        + "GPU: " + gpuInfo + "\n"
                        + "RAM: " + ramInfo + "\n"
                        + "OS: " + operatingSystem + "\n"
                        + "Memória em uso: " + memoryUsage + "\n");


        ArrayDateFormatterController arrayDateFormatterController = new ArrayDateFormatterController(
                ServiceRegistry.getInstance().getService(IDateFormatter.class)
        );

        IFileRepository<String[]> fileRepository = ServiceRegistry.getInstance().getService(IFileRepository.class);

        String[] data = fileRepository.readFile("src/main/resources/b3_stocks_1994_2020.csv");

        Result result = arrayDateFormatterController.formatArrayDate(data, "dd/MM/yyyy");

        System.out.println(result.message);

        if (result.status) {

            Result fileResult = fileRepository.writeFile("src/main/resources/b3stocks_T1.csv", data);

            System.out.println(fileResult.message);
        }


        Result filterInitial = resultRepository.result(
                "Aplicando filtro para aquele que possuir o maior volume negociado em bolsa.",
                true,
                null);

        System.out.println(filterInitial.message);

        IStockFilterService stockFilterService = ServiceRegistry.getInstance().getService(IStockFilterService.class);

        Result filteredData = stockFilterService.filterByMaxVolume(data);

        System.out.println(filteredData.message);

        if (filteredData.status && filteredData.data != null) {
            Result fileResult = fileRepository.writeFile("src/main/resources/b3stocks_F1.csv", (String[]) filteredData.data);

            System.out.println(fileResult.message);
        }

        Result filterMediaDiaria = resultRepository.result(
                "Aplicando filtro para apenas os registros que possuírem volume negociado acima da média diária.",
                true,
                null);

        System.out.println(filterMediaDiaria.message);

        Result filteredData2 = stockFilterService.filterByAboveAverageVolume(data);

        System.out.println(filteredData2.message);

        if (filteredData2.status && filteredData2.data != null) {
            Result fileResult = fileRepository.writeFile("src/main/resources/b3stocks_F2.csv", (String[]) filteredData2.data);

            System.out.println(fileResult.message);
        }

        SortController<String[][]> sortController = new SortController<String[][]>(
                ServiceRegistry.getInstance().getService(IArrayService.class)
        );

        AlgorithmsEnum[] AlgorithmsEnums = new AlgorithmsEnum[]{
//                AlgorithmsEnum.BUBBLE_SORT,
//                AlgorithmsEnum.SELECTION_SORT,
//                AlgorithmsEnum.QUICK_SORT,
//                AlgorithmsEnum.QUICK_SORT_MEDIAN_OF_THREE,
//                AlgorithmsEnum.MERGE_SORT,
//                AlgorithmsEnum.HEAP_SORT,
//                AlgorithmsEnum.COUNTING_SORT,

                AlgorithmsEnum.HASH_SORT,
                AlgorithmsEnum.QUEUE_SORT,
                AlgorithmsEnum.TREE_SORT
        };

        Result sortingInitial = resultRepository.result(
                "Aplicando os algoritmos de ordenação aos dados.",
                true,
                null);

        System.out.println(sortingInitial.message);

        sortB3Ticker(data, AlgorithmsEnums, sortController, fileRepository);

        Result sortingTicker = resultRepository.result(
                "Algoritmos de ordenação aos dados por tickers finalizados.",
                true,
                null);

        System.out.println(sortingTicker.message);

        sortB3Volume(data, AlgorithmsEnums, sortController, fileRepository);

        Result sortingVolume = resultRepository.result(
                "Algoritmos de ordenação aos dados por volume finalizados.",
                true,
                null);

        System.out.println(sortingVolume.message);

        sortB3Fluctuations(data, AlgorithmsEnums, sortController, fileRepository);

        Result sortingFluctuations = resultRepository.result(
                "Algoritmos de ordenação aos dados por fluctuation (high - low) finalizados.",
                true,
                null);

        System.out.println(
                sortingFluctuations.message + "\n"
        );

        Result messageFinal = resultRepository.result("Aplicação finalizada.", true, null);

        System.out.println(messageFinal.message);

    }

    /**
     * Sorts the given array of strings representing B3 stocks data by ticker name,
     * using the given sorting algorithms and writes the result to a CSV file
     * for each algorithm.
     *
     * @param data            the array of strings to be sorted
     * @param AlgorithmsEnums the sorting algorithms to be used
     * @param sortController  the controller for the sorting algorithms
     * @param fileRepository  the repository for writing the result to a CSV file
     */
    private static void sortB3Ticker(String[] data, AlgorithmsEnum[] AlgorithmsEnums, SortController<String[][]> sortController, IFileRepository<String[]> fileRepository) {
        String[][] data2 = new String[data.length][6];

        for (int i = 0; i < data.length; i++) {
            String[] row = data[i].split(",");
            data2[i] = row;
        }

        Comparator<Object> comparator = (o1, o2) -> {
            if (o1 instanceof String[] && o2 instanceof String[]) {
                String[] row1 = (String[]) o1;
                String[] row2 = (String[]) o2;

                // Comparar pelo segundo elemento do array
                return row1[1].compareTo(row2[1]);
            }
            throw new IllegalArgumentException("Objects are not String[] arrays.");
        };


        for (AlgorithmsEnum algorithm : AlgorithmsEnums) {
            Result resultSort = sortController.sorting(algorithm, data2, comparator);
            if (resultSort.status && resultSort.data != null) {

                String[][] dataTicker = (String[][]) resultSort.data;
                String[] strings = new String[dataTicker.length];

                for (int i = 0; i < strings.length; i++) {
                    strings[i] = String.join(",", dataTicker[i]);
                }

                Result fileResult = fileRepository.writeFile("src/main/resources/b3stocks_ticker_" + algorithm + ".csv", strings);

                System.out.println(fileResult.message);
            }
        }
    }

    /**
     * Sorts the given data by volume and writes the result to a CSV file using the given sorting algorithms.
     *
     * @param data            the data to be sorted
     * @param AlgorithmsEnums the sorting algorithms to be used
     * @param sortController  the controller for the sorting algorithms
     * @param fileRepository  the repository for writing the result to a CSV file
     */
    private static void sortB3Volume(String[] data, AlgorithmsEnum[] AlgorithmsEnums, SortController<String[][]> sortController, IFileRepository<String[]> fileRepository) {
        String[][] data2 = new String[data.length][6];

        for (int i = 0; i < data.length; i++) {
            String[] row = data[i].split(",");
            data2[i] = row;
        }

        Comparator<Object> comparator = (o1, o2) -> {
            if (o1 instanceof String[] && o2 instanceof String[]) {
                String[] row1 = (String[]) o1;
                String[] row2 = (String[]) o2;

                // Comparar pelo volume (índice 5)
                double volume1 = Double.parseDouble(row1[6]);
                double volume2 = Double.parseDouble(row2[6]);
                return Double.compare(volume1, volume2);
            }
            throw new IllegalArgumentException("Objects are not String[] arrays.");
        };

        for (AlgorithmsEnum algorithm : AlgorithmsEnums) {
            Result resultSort = sortController.sorting(algorithm, data2, comparator);
            if (resultSort.status && resultSort.data != null) {
                String[][] dataTicker = (String[][]) resultSort.data;
                String[] strings = new String[dataTicker.length];

                for (int i = 0; i < strings.length; i++) {
                    strings[i] = String.join(",", dataTicker[i]);
                }

                Result fileResult = fileRepository.writeFile("src/main/resources/b3stocks_Volume_" + algorithm + ".csv", strings);
                System.out.println(fileResult.message);
            }
        }
    }

    /**
     * Sorts the given array of strings representing B3 stocks data by fluctuation
     * (high - low), using the given sorting algorithms and writes the result to a CSV file
     * for each algorithm.
     *
     * @param data            the array of strings to be sorted
     * @param AlgorithmsEnums the sorting algorithms to be used
     * @param sortController  the controller for the sorting algorithms
     * @param fileRepository  the repository for writing the result to a CSV file
     */
    private static void sortB3Fluctuations(String[] data, AlgorithmsEnum[] AlgorithmsEnums, SortController<String[][]> sortController, IFileRepository<String[]> fileRepository) {
        String[][] data2 = new String[data.length][6];

        for (int i = 0; i < data.length; i++) {
            String[] row = data[i].split(",");
            data2[i] = row;
        }

        Comparator<Object> comparator = (o1, o2) -> {
            if (o1 instanceof String[] && o2 instanceof String[]) {
                String[] row1 = (String[]) o1;
                String[] row2 = (String[]) o2;

                // Converter os valores de high e low para double
                double high1 = Double.parseDouble(row1[4]);
                double low1 = Double.parseDouble(row1[5]);
                double high2 = Double.parseDouble(row2[4]);
                double low2 = Double.parseDouble(row2[5]);

                // Calcular a variação diária (high - low)
                double fluctuation1 = high1 - low1;
                double fluctuation2 = high2 - low2;

                // Comparar pela maior variação, do maior para o menor
                return Double.compare(fluctuation2, fluctuation1); // Maior para menor
            }
            throw new IllegalArgumentException("Objects are not String[] arrays.");
        };


        for (AlgorithmsEnum algorithm : AlgorithmsEnums) {
            Result resultSort = sortController.sorting(algorithm, data2, comparator);
            if (resultSort.status && resultSort.data != null) {

                String[][] dataTicker = (String[][]) resultSort.data;
                String[] strings = new String[dataTicker.length];

                for (int i = 0; i < strings.length; i++) {
                    strings[i] = String.join(",", dataTicker[i]);
                }

                Result fileResult = fileRepository.writeFile("src/main/resources/b3stocks_fluctuations_" + algorithm + ".csv", strings);

                System.out.println(fileResult.message);
            }
        }
    }


}
