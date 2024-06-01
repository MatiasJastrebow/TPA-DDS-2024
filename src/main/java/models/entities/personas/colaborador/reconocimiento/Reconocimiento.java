package models.entities.personas.colaborador.reconocimiento;

import lombok.Getter;
import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.reconocimiento.formula.imp.Formula;

/**
 * Representa el reconocimiento que reciben los colaboradores.
 */

public class Reconocimiento {
  @Getter
  private Float puntosPorColaborar;
  @Setter
  private Formula formulaCalculoDePuntos;

  public Reconocimiento() {
    this.puntosPorColaborar = 0f;
  }

  public void sumarPuntos(Colaboracion colaboracion) {
    puntosPorColaborar += formulaCalculoDePuntos.calcularPuntosDe(colaboracion);
  }

  public void restarPuntos(Float puntos) {
    puntosPorColaborar -= puntos;
  }
}