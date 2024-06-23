package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la apertura real de una heladera y lo que conlleva en el sistema.
 */

@Setter
@Getter
public class Apertura {
  private LocalDateTime fechaApertura;
  private LocalDateTime fechaSolicitud;

  public Apertura(LocalDateTime solicitud) {
    this.fechaSolicitud = solicitud;
  }
}
