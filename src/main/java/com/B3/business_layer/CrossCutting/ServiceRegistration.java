package com.B3.business_layer.CrossCutting;

import com.B3.business_layer.Business.Repositories.Array.*;
import com.B3.business_layer.Business.Repositories.FileRepository;
import com.B3.business_layer.Business.Repositories.Hash.HashRepository;
import com.B3.business_layer.Business.Repositories.Queue.QueueRepository;
import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.business_layer.Business.Repositories.Tree.TreeRepository;
import com.B3.business_layer.Business.Services.DateFormatterService;
import com.B3.business_layer.Business.Services.SortService;
import com.B3.business_layer.Business.Services.StockFilterService;
import com.B3.domain_layer.Domain.Repositories.IFileRepository;
import com.B3.domain_layer.Domain.Repositories.IResultRepository;
import com.B3.domain_layer.Domain.Services.IDateFormatter;
import com.B3.domain_layer.Domain.Services.IStockFilterService;
import com.B3.domain_layer.DomainArray.Repositories.*;
import com.B3.domain_layer.DomainArray.Services.IArrayService;
import com.B3.domain_layer.DomainHash.Repositories.IHashRepository;
import com.B3.domain_layer.DomainQueue.Repositories.IQueueRepository;
import com.B3.domain_layer.DomainTree.Repositories.ITreeRepository;

public class ServiceRegistration<T> {

    @SuppressWarnings("unchecked")
    public void addServices() {
        ServiceRegistry registry = ServiceRegistry.getInstance();

        registry.addSingleton(IFileRepository.class, new FileRepository());
        registry.addSingleton(IResultRepository.class, new ResultRepository());

        registry.addSingleton(IBubbleSortArrayRepository.class, new BubbleSortArrayRepository<T>());
        registry.addSingleton(ICountingSortArrayRepository.class, new CountingSortArrayRepository<T>());
        registry.addSingleton(IHeapSortArrayRepository.class, new HeapSortArrayRepository<T>());
        registry.addSingleton(IMergeSortArrayRepository.class, new MergeSortArrayRepository<T>());
        registry.addSingleton(IQuickSortArrayRepository.class, new QuickSortArrayRepository<T>());
        registry.addSingleton(IQuickSortMedianOfThreeArrayRepository.class, new QuickSortMedianOfThreeArrayRepository<T>());
        registry.addSingleton(ISelectionSortArrayRepository.class, new SelectionSortArrayRepository<T>());

        registry.addSingleton(IQueueRepository.class, new QueueRepository<T>());
        registry.addSingleton(IHashRepository.class, new HashRepository<T>());
        registry.addSingleton(ITreeRepository.class, new TreeRepository<T>());

        registry.addSingleton(IDateFormatter.class, new DateFormatterService());

        registry.addSingleton(IArrayService.class, new SortService<T>(
                registry.getService(IFileRepository.class),
                registry.getService(IBubbleSortArrayRepository.class),
                registry.getService(ICountingSortArrayRepository.class),
                registry.getService(IHeapSortArrayRepository.class),
                registry.getService(IMergeSortArrayRepository.class),
                registry.getService(IQuickSortArrayRepository.class),
                registry.getService(IQuickSortMedianOfThreeArrayRepository.class),
                registry.getService(ISelectionSortArrayRepository.class),
                registry.getService(IQueueRepository.class),
                registry.getService(IHashRepository.class),
                registry.getService(ITreeRepository.class)

        ));

        registry.addSingleton(IStockFilterService.class, new StockFilterService());

    }

}
