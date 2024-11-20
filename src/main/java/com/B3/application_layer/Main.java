package com.B3.application_layer;

import com.B3.application_layer.Controllers.SortController;
import com.B3.business_layer.CrossCutting.ServiceRegistration;
import com.B3.business_layer.CrossCutting.ServiceRegistry;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.DomainArray.Services.IArrayService;

import java.util.Arrays;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        new ServiceRegistration<Integer[]>().addServices();

        SortController<Integer[]> sortController = new SortController<Integer[]>(
                ServiceRegistry.getInstance().getService(IArrayService.class)
        );

        AlgorithmsEnum[] AlgorithmsEnums = new AlgorithmsEnum[]{
                AlgorithmsEnum.BUBBLE_SORT,
                AlgorithmsEnum.SELECTION_SORT,
                AlgorithmsEnum.QUICK_SORT,
                AlgorithmsEnum.QUICK_SORT_MEDIAN_OF_THREE,
                AlgorithmsEnum.MERGE_SORT,
                AlgorithmsEnum.HEAP_SORT,
                AlgorithmsEnum.COUNTING_SORT,

                AlgorithmsEnum.HASH_SORT,
                AlgorithmsEnum.QUEUE_SORT,
                AlgorithmsEnum.TREE_SORT
        };

        for (AlgorithmsEnum algorithm : AlgorithmsEnums) {
            Result result = sortController.sorting(algorithm, new Integer[]{1, 9, 5, 1, 5, 6, 7, 8, 9, 10});
            System.out.println(result.message);
            if (result.data instanceof Integer[]) {
                // Cast the data to Integer[] and print using Arrays.toString()
                System.out.println(Arrays.toString((Integer[]) result.data));
            } else {
                // Handle other types if needed
                System.out.println(result.data);
            }
        }

    }
}
