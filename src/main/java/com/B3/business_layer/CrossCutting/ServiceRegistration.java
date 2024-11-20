package com.B3.business_layer.CrossCutting;

import com.B3.business_layer.Business.Repositories.Array.*;
import com.B3.business_layer.Business.Repositories.ResultRepository;
import com.B3.business_layer.Business.Repositories.WriteArrayRepository;
import com.B3.business_layer.Business.Services.ArrayService;
import com.B3.domain_layer.Domain.Repositories.IResultRepository;
import com.B3.domain_layer.Domain.Repositories.IWriteArrayRepository;
import com.B3.domain_layer.DomainArray.Repositories.*;
import com.B3.domain_layer.DomainArray.Services.IArrayService;

public class ServiceRegistration<T> {
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

        registry.addSingleton(IArrayService.class, new ArrayService<T>(
                registry.getService(IWriteArrayRepository.class),
                registry.getService(IBubbleSortArrayRepository.class),
                registry.getService(ICountingSortArrayRepository.class),
                registry.getService(IHeapSortArrayRepository.class),
                registry.getService(IMergeSortArrayRepository.class),
                registry.getService(IQuickSortArrayRepository.class),
                registry.getService(IQuickSortMedianOfThreeArrayRepository.class),
                registry.getService(ISelectionSortArrayRepository.class)
        ));

    }

}
