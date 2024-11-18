package com.B3.business_layer.Business.Services;

import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.Domain.Repositories.IWriteArrayRepository;
import com.B3.domain_layer.DomainLinkedList.Repositories.*;
import com.B3.domain_layer.DomainLinkedList.Services.ILinkedListService;

import java.util.HashMap;
import java.util.Map;

public class LinkedListService<T> implements ILinkedListService<T> {

    private final IWriteArrayRepository<T> writeArrayRepository;
    private final ResultRepository resultRepository;
    private final Map<AlgorithmsEnum, IBaseSortLinkedListRepository<T>> algorithmRepositoryMap;

    public LinkedListService(
            IWriteArrayRepository<T> writeArrayRepository,
            IBubbleSortLinkedListRepository<T> bubbleSortLinkedListRepository,
            ICountingSortLinkedListRepository<T> countingSortLinkedListRepository,
            IHeapSortLinkedListRepository<T> heapSortLinkedListRepository,
            IMergeSortLinkedListRepository<T> mergeSortLinkedListRepository,
            IQuickSortLinkedListRepository<T> quickSortLinkedListRepository,
            IQuickSortMedianOfThreeLinkedListRepository<T> quickSortMedianOfThreeLinkedListRepository,
            ISelectionSortLinkedListRepository<T> selectionSortLinkedListRepository
    ) {
        this.writeArrayRepository = writeArrayRepository;
        resultRepository = new ResultRepository();

        this.algorithmRepositoryMap = new HashMap<>();
        this.algorithmRepositoryMap.put(AlgorithmsEnum.BUBBLE_SORT, bubbleSortLinkedListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.COUNTING_SORT, countingSortLinkedListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.HEAP_SORT, heapSortLinkedListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.MERGE_SORT, mergeSortLinkedListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.QUICK_SORT, quickSortLinkedListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.QUICK_SORT_MEDIAN_OF_THREE, quickSortMedianOfThreeLinkedListRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.SELECTION_SORT, selectionSortLinkedListRepository);
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
