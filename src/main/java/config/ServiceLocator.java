package config;

import java.util.HashMap;
import models.repositories.imp.ColaboradoresRepository;
import services.ColaboradoresService;
import services.DireccionesService;

/**
 * Clase serviceLocator para obtener los services.
 */

public class ServiceLocator {
  private static final HashMap<String, Object> instances = new HashMap<>();

  /**
   * Obtiene la instancia de un Service.
   *
   * @param serviceClass la clase del Service.
   * @param <T> el tipo de la clase del Service.
   * @return la instancia del Service.
   */

  @SuppressWarnings("unchecked")
  public static <T> T instanceOf(Class<T> serviceClass) {
    String serviceName = serviceClass.getName();

    if (!instances.containsKey(serviceName)) {
      if (serviceClass.equals(ColaboradoresService.class)) {
        ColaboradoresService instance =
            new ColaboradoresService(RepositoryLocator.instanceOf(ColaboradoresRepository.class));
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(DireccionesService.class)) {
        DireccionesService instance = new DireccionesService();
        instances.put(serviceName, instance);
      }
    }

    return (T) instances.get(serviceName);
  }
}