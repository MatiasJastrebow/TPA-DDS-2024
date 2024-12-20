package config;

import java.util.HashMap;
import models.repositories.imp.TecnicosRepository;
import models.searchers.BuscadorHeladerasFrecuentes;
import models.searchers.BuscadorTecnicosCercanos;
import utils.recomendator.adapter.AdapterServicioRecomendacion;
import utils.reportes.GeneradorReporte;
import utils.security.Autenticador;
import utils.security.rules.CantidadMaximaDeCaracteres;
import utils.security.rules.CantidadMinimaDeCaracteres;
import utils.security.rules.PerteneceAlArchivo;
import utils.security.rules.TieneCaracteresEspeciales;
import utils.security.rules.TieneMayusculasMinusculas;
import utils.security.rules.TieneNumeros;

/**
 * Clase serviceLocator para obtener servicios de la carpeta utils.
 */

public class UtilsLocator {
  private static final HashMap<String, Object> instances = new HashMap<>();

  /**
   * Obtiene la instancia de un util.
   *
   * @param utilClass la clase del util.
   * @param <T> el tipo de la clase del util.
   * @return la instancia del util.
   */

  @SuppressWarnings("unchecked")
  public static <T> T instanceOf(Class<T> utilClass) {
    String utilName = utilClass.getName();

    if (!instances.containsKey(utilName)) {
      if (utilClass.equals(Autenticador.class)) {
        Autenticador instance = new Autenticador();
        //SE LE ASIGNAN ESTAS POLITICAS POR DEFAULT
        //En caso de querer otras, avisen
        instance.agregarPoliticas(
            new CantidadMaximaDeCaracteres(64),
            new CantidadMinimaDeCaracteres(8),
            new PerteneceAlArchivo(
                "Top10milPeoresContrasenias",
                "src/main/resources/passwordValidator/top10000.txt"
            ),
            new TieneMayusculasMinusculas(),
            new TieneNumeros(),
            new TieneCaracteresEspeciales());
        instances.put(utilName, instance);

      } else if (utilClass.equals(BuscadorTecnicosCercanos.class)) {
        BuscadorTecnicosCercanos instance = new BuscadorTecnicosCercanos(
            RepositoryLocator.instanceOf(TecnicosRepository.class)
        );
        instances.put(utilName, instance);

      } else if (utilClass.equals(GeneradorReporte.class)) {
        GeneradorReporte instance = new GeneradorReporte();
        instances.put(utilName, instance);

      } else if (utilClass.equals(AdapterServicioRecomendacion.class)) {
        AdapterServicioRecomendacion instance = new AdapterServicioRecomendacion();
        instances.put(utilName, instance);

      } else if (utilClass.equals(BuscadorHeladerasFrecuentes.class)) {
        BuscadorHeladerasFrecuentes instance = new BuscadorHeladerasFrecuentes();
        instance.setCantidadRequerida(5); //Decision de diseño :P
        instances.put(utilName, instance);

      }
    }
    return (T) instances.get(utilName);
  }
}
