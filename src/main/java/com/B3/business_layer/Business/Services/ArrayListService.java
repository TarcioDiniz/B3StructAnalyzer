package com.B3.business_layer.Business.Services;

import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.Domain.Repositories.IWriteArrayRepository;
import com.B3.domain_layer.DomainArrayList.Repositories.*;
import com.B3.domain_layer.DomainArrayList.Services.IArrayListService;

import java.util.HashMap;
import java.util.Map;

public class ArrayListService<T> implements IArrayListService<T> {

    private final IWriteArrayRepository<T> writeArrayRepository;
    private final ResultRepository resultRepository;
    private final Map<AlgorithmsEnum, IBaseSortArrayListRepository<T>> algorithmRepositoryMap;


    public ArrayListService(
            IWriteArrayRepository<T> writeArrayRepository,
            IBubbleSortArrayListRepository<T> bubbleSortArrayListRepository,
            ICountingSortArrayListRepository<T> countingSortArrayListRepository,
            IHeapSortArrayListRepository<T> heapSortArrayListRepository,
            IMergeSortArrayListRepository<T> mergeSortArrayListRepository,
            IQuickSortArrayListRepository<T> quickSortArrayListRepository,
            IQuickSortMedianOfThreeArrayListRepository<T> quickSortMedianOfThreeArrayListRepository,
            ISelectionSortArrayListRepository<T> selectionSortArrayListRepository
    ) {
        this.writeArrayRepository = writeArrayRepository;
        this.resultRepository = new ResultRepository();

        this.algorithmRepositoryMap = new HashMap<>();
        this.algorithmRepositoryMap.put(AlgorithmsEnum.BUBBLE_SORT, bubbleSortArrayListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.COUNTING_SORT, countingSortArrayListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.HEAP_SORT, heapSortArrayListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.MERGE_SORT, mergeSortArrayListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.QUICK_SORT, quickSortArrayListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.QUICK_SORT_MEDIAN_OF_THREE, quickSortMedianOfThreeArrayListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.SELECTION_SORT, selectionSortArrayListRepository);
    }


    @Override
    public Result sort(AlgorithmsEnum algorithm, T array) {
        try {
            T result = algorithmRepositoryMap.get(algorithm).sort(array);
            this.writeArrayRepository.writeFile("array.txt", result);
            String message = algorithm.toString() + " completed";
            return resultRepository.result(message, true, result);
        } catch (Exception e) {
            String errorMessage = "Error occurred while sorting using " + algorithm + ": " + e.getMessage();
            return resultRepository.result(errorMessage, false, null);
        }
    }

}