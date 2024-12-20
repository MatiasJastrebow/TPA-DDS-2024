package models.repositories.imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import models.entities.reporte.ReporteHeladera;

/**
 * Clase que representa a los repositorios de reportes de heladeras.
 */

public class ReportesHeladerasRepository extends GenericRepository {

  public void guardar(ReporteHeladera reporte) {
    super.guardar(reporte);
  }

  public void modificar(ReporteHeladera reporte) {
    super.modificar(reporte);
  }

  public void eliminarFisico(ReporteHeladera reporte) {
    super.eliminarFisico(reporte);
  }

  public void eliminar(ReporteHeladera reporte) {
    super.eliminar(reporte);
  }

  public Optional<ReporteHeladera> buscarPorId(Long id) {
    return super.buscarPorId(id, ReporteHeladera.class);
  }

  public List<ReporteHeladera> buscarTodos() {
    return super.buscarTodos(ReporteHeladera.class);
  }

  /**
   * Busca todos los reportes de heladera en la base de datos.
   *
   * @return Lista de reportes de heladera.
   */

  public Optional<ReporteHeladera> buscarSemanalPorHeladera(Long id) {
    LocalDate haceUnaSemana = LocalDate.now().minusWeeks(1);

    ReporteHeladera resultado = entityManager()
        .createQuery(
            "SELECT r FROM ReporteHeladera r "
                + "WHERE r.heladera.id = :id AND r.fecha >= :haceUnaSemana",
            ReporteHeladera.class
        )
        .setParameter("id", id)
        .setParameter("haceUnaSemana", haceUnaSemana)
        .getResultList()
        .stream()
        .findFirst()
        .orElse(null);

    return Optional.ofNullable(resultado);
  }

  /**
   * Busca todos los reportes de heladera en la base de datos.
   *
   * @return Lista de reportes de heladera.
   */

  public List<ReporteHeladera> buscarTodosUltimaSemana() {
    LocalDate hoy = LocalDate.now();
    LocalDate haceUnaSemana = hoy.minusWeeks(1);

    return entityManager()
        .createQuery(
            "FROM ReporteHeladera WHERE fecha BETWEEN :haceUnaSemana AND :hoy",
            ReporteHeladera.class
        )
        .setParameter("haceUnaSemana", haceUnaSemana)
        .setParameter("hoy", hoy)
        .getResultList();
  }
}
