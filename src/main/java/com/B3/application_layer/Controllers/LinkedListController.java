package com.B3.application_layer.Controllers;

import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.DomainLinkedList.Services.ILinkedListService;

public class LinkedListController<T> {

    private final ILinkedListService<T> linkedListService;

    public LinkedListController(
            ILinkedListService<T> linkedListService
    ) {
        this.linkedListService = linkedListService;
    }

    public Result sorting(AlgorithmsEnum algorithms, T array) {
        return linkedListService.sort(algorithms, array);
    }

}
