package com.B3.domain_layer.Domain.Services;

import com.B3.domain_layer.Domain.Dtos.Result;

public interface IDateFormatter {
    String formatDate(String date, String format);

    Result formatArrayDate(String[] data, String format);
}
