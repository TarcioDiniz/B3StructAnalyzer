package com.B3.application_layer;

import com.B3.application_layer.Controllers.ArrayDateFormatterController;
import com.B3.business_layer.CrossCutting.ServiceRegistration;
import com.B3.business_layer.CrossCutting.ServiceRegistry;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Repositories.IFileRepository;
import com.B3.domain_layer.Domain.Repositories.IResultRepository;
import com.B3.domain_layer.Domain.Services.IDateFormatter;
import com.B3.domain_layer.Domain.Services.IStockFilterService;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        new ServiceRegistration<String[]>().addServices();

        IResultRepository resultRepository = ServiceRegistry.getInstance().getService(IResultRepository.class);

        Result messageInitial = resultRepository.result("Inicializando aplicação.", true, null);

        System.out.println(messageInitial.message);

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

        Result messageFinal = resultRepository.result("Aplicação finalizada.", true, null);

        System.out.println(messageFinal.message);

    }
}
