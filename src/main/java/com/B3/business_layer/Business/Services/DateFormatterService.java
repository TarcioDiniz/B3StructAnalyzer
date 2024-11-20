package com.B3.business_layer.Business.Services;

import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.CsvColumnEnum;
import com.B3.domain_layer.Domain.Services.IDateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatterService implements IDateFormatter {

    private final ResultRepository resultRepository = new ResultRepository();

    @Override
    public String formatDate(String date, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return localDate.format(formatter);
        } catch (Exception e) {
            System.out.println("Erro ao formatar a data: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Result formatArrayDate(String[] data, String format) {
        try {
            int dateColumnIndex = -1;

            // Encontra o índice da coluna DATETIME
            for (int i = 0; i < CsvColumnEnum.values().length; i++) {
                if (CsvColumnEnum.values()[i].getColumnName().equalsIgnoreCase("DATETIME")) {
                    dateColumnIndex = i;
                    break;
                }
            }

            // Verifica se a coluna DATETIME foi encontrada
            if (dateColumnIndex == -1) {
                return resultRepository.result("Coluna DATETIME não encontrada no CSV.", false, null);
            }

            // Formata as datas de toda a coluna DATETIME
            for (int i = 0; i < data.length; i++) {
                String row = data[i];
                String[] columns = row.split(",");

                // Pega o valor da coluna DATETIME e formata
                String date = columns[dateColumnIndex];
                String formattedDate = this.formatDate(date, format);

                // Substitui o valor da coluna DATETIME pelo valor formatado
                columns[dateColumnIndex] = formattedDate;

                // Atualiza a linha com o valor formatado
                data[i] = String.join(",", columns);
            }

            return resultRepository.result("Data formatada com sucesso.", true, data);
        } catch (Exception e) {
            return resultRepository.result("Erro ao formatar a data: " + e.getMessage(), false, null);
        }
    }
}
