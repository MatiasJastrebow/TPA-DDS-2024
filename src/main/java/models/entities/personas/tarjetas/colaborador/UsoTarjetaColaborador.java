package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;
import models.entities.colaboracion.Colaboracion;
import models.entities.heladera.Heladera;

/**
 * Clase que representa un uso de la tarjeta de un colaborador.
 */

@Getter
@Setter
@Entity
@Table(name = "usos_tarjetas_colaboradores")
public class UsoTarjetaColaborador extends Persistente {

  @Embedded
  private Apertura apertura;

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id", nullable = false)
  private Heladera heladera;

  @ManyToOne
  @JoinColumn(name = "tarjetaColaborador_codigo", referencedColumnName = "id", nullable = false)
  private TarjetaColaborador tarjetaColaborador;

  @ManyToOne
  @JoinColumn(name = "colaboracion_id", referencedColumnName = "id")
  private Colaboracion colaboracionAsociada;

  /**
   * Instancia la clase de Uso.
   */

  public UsoTarjetaColaborador() {
    this.apertura = new Apertura(LocalDateTime.now());
  }

  public UsoTarjetaColaborador(Colaboracion colaboracion) {
    this.apertura = new Apertura(LocalDateTime.now());
    this.colaboracionAsociada = colaboracion;
  }
}
