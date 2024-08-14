package models.repositories.interfaces;

import models.entities.personas.tecnico.VisitaTecnica;

/**
 * Interfaz Repositorio para las Visitas Técnicas.
 */

public interface InterfaceVisitasRepository {
  void guardar(VisitaTecnica visitaTecnica);
}
