package com.B3.business_layer.Business.Services;

import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.domain_layer.Domain.Dtos.Result;
import com.B3.domain_layer.Domain.Enums.AlgorithmsEnum;
import com.B3.domain_layer.Domain.Repositories.IBaseSortArrayRepository;
import com.B3.domain_layer.Domain.Repositories.IFileRepository;
import com.B3.domain_layer.DomainArray.Repositories.*;
import com.B3.domain_layer.DomainArray.Services.IArrayService;
import com.B3.domain_layer.DomainQueue.Repositories.IQueueRepository;
import com.B3.domain_layer.DomainHash.Repositories.IHashRepository;
import com.B3.domain_layer.DomainTree.Repositories.ITreeRepository;

import java.util.HashMap;
import java.util.Map;

public class SortService<T> implements IArrayService<T> {

    private final IFileRepository<T> writeArrayRepository;
    private final ResultRepository resultRepository;
    private final Map<AlgorithmsEnum, IBaseSortArrayRepository<T>> algorithmRepositoryMap;

    public SortService(
            IFileRepository<T> writeArrayRepository,
            IBubbleSortArrayRepository<T> bubbleSortArrayRepository,
            ICountingSortArrayRepository<T> countingSortArrayRepository,
            IHeapSortArrayRepository<T> heapSortArrayRepository,
            IMergeSortArrayRepository<T> mergeSortArrayRepository,
            IQuickSortArrayRepository<T> quickSortArrayRepository,
            IQuickSortMedianOfThreeArrayRepository<T> quickSortMedianOfThreeArrayRepository,
            ISelectionSortArrayRepository<T> selectionSortArrayRepository,
            IQueueRepository<T> queueRepository,
            IHashRepository<T> hashSortRepository,
            ITreeRepository<T> treeSortRepository
    ) {
        this.writeArrayRepository = writeArrayRepository;
        resultRepository = new ResultRepository();

        this.algorithmRepositoryMap = new HashMap<>();
        this.algorithmRepositoryMap.put(AlgorithmsEnum.BUBBLE_SORT, bubbleSortArrayRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.COUNTING_SORT, countingSortArrayRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.HEAP_SORT, heapSortArrayRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.MERGE_SORT, mergeSortArrayRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.QUICK_SORT, quickSortArrayRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.QUICK_SORT_MEDIAN_OF_THREE, quickSortMedianOfThreeArrayRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.SELECTION_SORT, selectionSortArrayRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.QUEUE_SORT, queueRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.HASH_SORT, hashSortRepository);
        this.algorithmRepositoryMap.put(AlgorithmsEnum.TREE_SORT, treeSortRepository);
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
