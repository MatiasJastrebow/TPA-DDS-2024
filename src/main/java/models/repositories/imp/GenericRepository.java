package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.db.Persistente;
import models.repositories.PersistenciaSimple;

/**
 * Repositorio para persistir las entidades de dominio.
 */

public class GenericRepository implements PersistenciaSimple {

  public void guardar(Object objeto) {
    withTransaction(() -> entityManager().persist(objeto));
  }

  /**
   * Guarda una lista de objetos en la base de datos.
   *
   * @param objects Lista de objetos a guardar.
   */

  public void guardarColeccion(List<?> objects) {
    withTransaction(() -> {
      for (Object object : objects) {
        entityManager().persist(object);
      }
    });
  }

  public void modificar(Object objeto) {
    withTransaction(() -> entityManager().merge(objeto));
  }

  public void eliminarFisico(Object objeto) {
    withTransaction(() -> entityManager().remove(objeto));
  }

  /**
   * Elimina un objeto de manera logica de la base de datos.
   *
   * @param objeto Objeto a eliminar.
   */

  public void eliminar(Object objeto) {

    if (objeto instanceof Persistente persistente) {
      persistente.setActivo(false);
      this.modificar(persistente);
    } else {
      throw new IllegalArgumentException("El objeto no es una instancia de Persistente");
    }
  }

  public void refresh(Object objeto) {
    withTransaction(() -> entityManager().refresh(objeto));
  }

  public <T> Optional<T> buscarPorId(Long id, Class<T> clase) {
    return Optional.ofNullable(entityManager().find(clase, id));
  }

  /**
   * Busca todos los objetos de una clase.
   *
   * @param clase Clase de los objetos a buscar.
   * @param <T> Tipo de los objetos a buscar.
   * @return Lista de objetos.
   */

  public <T> List<T> buscarTodos(Class<T> clase) {
    return entityManager()
        .createQuery("FROM " + clase.getName() + " c WHERE c.activo = :activo", clase)
        .setParameter("activo", true)
        .getResultList();
  }

}
