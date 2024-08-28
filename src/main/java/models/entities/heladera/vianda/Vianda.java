package models.entities.heladera.vianda;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Representa una vianda que incluye una comida, fecha de donación, heladera, calorías,
 * peso y estado de entrega.
 */

@Getter
@NoArgsConstructor
@Entity
@Table(name = "viandas")
public class Vianda extends Persistente {

  @Embedded
  private Comida comida;

  @Column(name = "fechaDonacion", nullable = false)
  private LocalDate fechaDonacion;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", nullable = false)
  private Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "heladera_id", nullable = false)
  private Heladera heladera;

  @Column(name = "calorias", nullable = false)
  private Integer calorias;

  @Column(name = "peso", nullable = false)
  private Float peso;

  @Setter
  @Column(name = "estaEntregada")
  private Boolean entregada;

  /**
   * Constructor para la Vianda.
   */

  //TODO santi, cuando veas esto. no me acuerdo si este constructor era solo para el test o no
  // si era asi ponele el allArgs y borra esto
  public Vianda(Comida comida, LocalDate fechaDonacion, Colaborador colaborador,
                 Heladera heladera, Integer calorias, Float peso) {
    this.comida = comida;
    this.fechaDonacion = fechaDonacion;
    this.colaborador = colaborador;
    this.heladera = heladera;
    this.calorias = calorias;
    this.peso = peso;
    this.entregada = false;
  }

}