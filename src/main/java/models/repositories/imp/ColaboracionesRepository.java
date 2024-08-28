package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;
import models.repositories.interfaces.InterfaceColaboracionesRepository;

/**
 * Repositorio para las Colaboraciones.
 */

//TODO tiene sentido una interfaz en nuestro contexto?
@Getter
public class ColaboracionesRepository implements InterfaceColaboracionesRepository, WithSimplePersistenceUnit {

  public void guardar(Colaboracion... colaboraciones) {
    withTransaction(() -> {
      for (Colaboracion colaboracion : colaboraciones) {
        entityManager().persist(colaboracion);
      }
    });
  }

  public void guardar(Colaboracion colaboracion) {
    withTransaction(() -> {
      entityManager().persist(colaboracion);
    });
  }

  public void modificar(Colaboracion colaboracion) {
    withTransaction(() -> {
      entityManager().merge(colaboracion);
    });
  }

  public void eliminarFisico(Colaboracion colaboracion) {
    entityManager().remove(colaboracion);
  }

  public void eliminar(Colaboracion colaboracion) {
    colaboracion.setActivo(false);
    entityManager().merge(colaboracion);
  }

  public Optional<Colaboracion> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Colaboracion.class, id));
  }

  public List<Colaboracion> buscarPorTipo(TipoColaboracion tipoColaboracion) {
    return entityManager()
        .createQuery("FROM Colaboracion colab WHERE colab.tipoColaboracion = :tipo", Colaboracion.class)
        .setParameter("tipo", tipoColaboracion)
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Colaboracion> buscarTodos() {
    return entityManager()
        .createQuery("from " + Colaboracion.class.getName())
        .getResultList();
  }
}
