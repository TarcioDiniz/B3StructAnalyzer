package com.B3.business_layer.Business.Services;

import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.CsvColumnEnum;
import com.B3.domain_layer.Domain.Services.IStockFilterService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class StockFilterService implements IStockFilterService {

    private final ResultRepository resultRepository = new ResultRepository();

    @Override
    public Result filterByMaxVolume(String[] data) {
        try {
            // Mapa para armazenar o maior volume por data
            Map<String, String> maxVolumeMap = new HashMap<>();

            int dateColumnIndex = -1;
            int volumeColumnIndex = -1;

            // Encontra os índices das colunas DATETIME e VOLUME
            for (int i = 0; i < CsvColumnEnum.values().length; i++) {
                if (CsvColumnEnum.values()[i].getColumnName().equalsIgnoreCase("DATETIME")) {
                    dateColumnIndex = i;
                }
                if (CsvColumnEnum.values()[i].getColumnName().equalsIgnoreCase("VOLUME")) {
                    volumeColumnIndex = i;
                }
            }

            if (dateColumnIndex == -1 || volumeColumnIndex == -1) {
                throw new IllegalArgumentException("Colunas DATETIME ou VOLUME não encontradas no CSV.");
            }

            Map<String, String> filteredData = getStringMap(data, dateColumnIndex, volumeColumnIndex);

            // Retorna os dados filtrados como um array
            data = filteredData.values().toArray(new String[0]);

            return resultRepository.result("Dados filtrados com sucesso.", true, data);
        } catch (Exception e) {
            System.out.println("Erro ao filtrar dados: " + e.getMessage());
            return resultRepository.result("Erro ao filtrar dados.", false, null);
        }
    }

    private static Map<String, String> getStringMap(String[] data, int dateColumnIndex, int volumeColumnIndex) {
        Map<String, String> filteredData = new HashMap<>();

        // Processa os dados
        for (String row : data) {
            String[] columns = row.split(",");
            String date = columns[dateColumnIndex];
            String volumeStr = columns[volumeColumnIndex];

            try {
                // Converte o volume para BigDecimal
                BigDecimal volume = new BigDecimal(volumeStr);

                // Se ainda não existe uma entrada para essa data ou o volume é maior, substitui
                if (!filteredData.containsKey(date) || volume.compareTo(new BigDecimal(filteredData.get(date).split(",")[volumeColumnIndex])) > 0) {
                    filteredData.put(date, row);
                }
            } catch (NumberFormatException e) {
                // Se o volume não for válido (não pode ser convertido para BigDecimal), ignora a linha
                continue;
            }
        }

        return filteredData;
    }
}
