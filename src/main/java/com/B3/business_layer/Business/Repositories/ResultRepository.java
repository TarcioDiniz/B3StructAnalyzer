package com.B3.business_layer.Business.Repositories;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Repositories.IResultRepository;

public class ResultRepository implements IResultRepository {
    @Override
    public Result result(String message, Boolean status, Object data) {
        String className = getCallerClassName();
        String fullMessage = "[" + className + "] " + message;
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
