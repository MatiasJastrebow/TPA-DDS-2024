package utils;

import config.RepositoryLocator;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.GenericRepository;

/**
 *  Agrupa logica comun cuando instanciamos y guardamos una colaboracion.
 */

public class ColaboracionesHelper {

  /**
   * Helper method para la realizacion de una colaboracion.
   *
   * @param colaboracion una colaboracion.
   * @param colaborador un colaborador.
   */

  public static void realizarColaboracion(Colaboracion colaboracion, Colaborador colaborador) {
    colaboracion.setColaborador(colaborador);

    GenericRepository repository = RepositoryLocator.instanceOf(GenericRepository.class);

    repository.guardar(colaboracion);

    colaborador.agregarColaboracion(colaboracion);
    colaborador.aumentarReconocimiento(colaboracion);

    repository.modificar(colaborador);
  }
}
