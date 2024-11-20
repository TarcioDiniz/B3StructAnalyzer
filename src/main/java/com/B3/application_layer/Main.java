package com.B3.application_layer;

import com.B3.application_layer.Controllers.ArrayDateFormatterController;
import com.B3.business_layer.CrossCutting.ServiceRegistration;
import com.B3.business_layer.CrossCutting.ServiceRegistry;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Repositories.IFileRepository;
import com.B3.domain_layer.Domain.Repositories.IResultRepository;
import com.B3.domain_layer.Domain.Services.IDateFormatter;

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


    }
}
