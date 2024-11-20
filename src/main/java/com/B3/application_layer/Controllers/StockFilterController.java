package com.B3.application_layer.Controllers;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Services.IStockFilterService;

public class StockFilterController {

    IStockFilterService stockFilterService;

    public StockFilterController(IStockFilterService stockFilterService) {
        this.stockFilterService = stockFilterService;
    }

    public Result filterByMaxVolume(String[] data) {
        return stockFilterService.filterByMaxVolume(data);
    }

}
