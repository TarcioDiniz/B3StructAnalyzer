package com.B3.business_layer.Business.Repositories;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Repositories.IResultRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultRepository implements IResultRepository {
    @Override
    public Result result(String message, Boolean status, Object data) {
        String className = getCallerClassName();

        // Formata a data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formattedDate = sdf.format(new Date());

        // Sequências de escape ANSI para cores
        String reset = "\u001B[0m"; // Reseta a cor
        String green = "\u001B[32m"; // Cor verde
        String red = "\u001B[31m"; // Cor vermelha
        String blue = "\u001B[34m"; // Cor azul

        // Monta a mensagem
        String fullMessage = blue + "[" + className + "] " + (status ? green : red) + message + " " + reset + formattedDate;

        // Retorna o resultado com a mensagem colorida
        return new Result(fullMessage, status, data);
    }

    private String getCallerClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (int i = 2; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (!className.equals(this.getClass().getName()) && !className.contains("java.lang.Thread")) {
                return className.substring(className.lastIndexOf('.') + 1);
            }
        }
        return "Unknown";
    }

}
