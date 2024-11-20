package com.B3.domain_layer.Domain.Services;

public interface ISystemInfoCollector {
    String getModel();

    String getCpuInfo();

    String getGpuInfo();

    String getRamInfo();

    String getMemoryUsage();

    String getOperatingSystem();
}
