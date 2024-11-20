package com.B3.business_layer.Business.Services;

import com.B3.domain_layer.Domain.Services.ISystemInfoCollector;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;

import java.util.List;

public class SystemInfoCollectorService implements ISystemInfoCollector {
    private final SystemInfo systemInfo;

    public SystemInfoCollectorService() {
        systemInfo = new SystemInfo();
    }

    public String getModel() {
        return systemInfo.getHardware().getComputerSystem().getModel();
    }

    public String getCpuInfo() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        return processor.getProcessorIdentifier().getName();
    }

    public String getGpuInfo() {
        List<GraphicsCard> graphicsCards = systemInfo.getHardware().getGraphicsCards();
        return graphicsCards.isEmpty() ? "No GPU detected" : graphicsCards.get(0).getName();
    }

    public String getRamInfo() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long total = memory.getTotal();
        return (total / (1024 * 1024)) / 1000 + " GB";
    }

    public String getMemoryUsage() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;
        return (Math.abs(used) / (1024 * 1024)) + " MB / " + (total / (1024 * 1024)) + " MB";
    }

    public String getOperatingSystem() {
        return systemInfo.getOperatingSystem().toString();
    }


}