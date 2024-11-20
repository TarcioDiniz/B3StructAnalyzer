package com.B3.business_layer.CrossCutting;

import com.B3.business_layer.Business.Repositories.Array.*;
import com.B3.business_layer.Business.Repositories.Hash.HashRepository;
import com.B3.business_layer.Business.Repositories.Queue.QueueRepository;
import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.business_layer.Business.Repositories.Tree.TreeRepository;
import com.B3.business_layer.Business.Repositories.WriteArrayRepository;
import com.B3.business_layer.Business.Services.SortService;
import com.B3.domain_layer.Domain.Repositories.IResultRepository;
import com.B3.domain_layer.Domain.Repositories.IWriteArrayRepository;
import com.B3.domain_layer.DomainArray.Repositories.*;
import com.B3.domain_layer.DomainArray.Services.IArrayService;
import com.B3.domain_layer.DomainHash.Repositories.IHashRepository;
import com.B3.domain_layer.DomainQueue.Repositories.IQueueRepository;
import com.B3.domain_layer.DomainTree.Repositories.ITreeRepository;

public class ServiceRegistration<T> {

    @SuppressWarnings("unchecked")
    public void addServices() {
        ServiceRegistry registry = ServiceRegistry.getInstance();

        registry.addSingleton(IWriteArrayRepository.class, new WriteArrayRepository<T>());
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

        registry.addSingleton(IArrayService.class, new SortService<T>(
                registry.getService(IWriteArrayRepository.class),
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

    }

}
