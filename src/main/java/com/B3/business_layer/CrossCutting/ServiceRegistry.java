package com.B3.business_layer.CrossCutting;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {

    // Mapa para armazenar instâncias únicas de serviços
    private static final Map<Class<?>, Object> services = new HashMap<>();

    // Instância única do próprio registro
    private static final ServiceRegistry instance = new ServiceRegistry();

    // Construtor privado para impedir instanciação externa
    private ServiceRegistry() {
    }

    // Método para obter a instância única do registro
    public static ServiceRegistry getInstance() {
        return instance;
    }

    /**
     * Método para registrar um serviço como singleton.
     *
     * @param interfaceType  A interface do serviço
     * @param implementation Instância da implementação
     * @param <T>            O tipo da interface
     */
    public <T> void addSingleton(Class<T> interfaceType, T implementation) {
        if (services.containsKey(interfaceType)) {
            throw new IllegalStateException("Service already registered: " + interfaceType.getName());
        }
        services.put(interfaceType, implementation);
    }


    /**
     * Método para obter um serviço registrado.
     *
     * @param interfaceType A interface do serviço
     * @param <T>           O tipo da interface
     * @return A instância registrada
     */
    public <T> T getService(Class<T> interfaceType) {
        return interfaceType.cast(services.get(interfaceType));
    }

    /**
     * Método para limpar todos os serviços registrados.
     */
    public void clearAllServices() {
        services.clear();
    }
}
