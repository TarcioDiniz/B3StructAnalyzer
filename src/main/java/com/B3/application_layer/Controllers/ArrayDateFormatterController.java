package com.B3.application_layer.Controllers;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Services.IDateFormatter;

public class ArrayDateFormatterController {

    private final IDateFormatter dateFormatterService;

    public ArrayDateFormatterController(IDateFormatter dateFormatterService) {
        this.dateFormatterService = dateFormatterService;
    }


    public String formatArrayDate(String date, String format) {
        return dateFormatterService.formatDate(date, format);
    }

    public Result formatArrayDate(String[] date, String format) {
        return dateFormatterService.formatArrayDate(date, format);
    }

}
