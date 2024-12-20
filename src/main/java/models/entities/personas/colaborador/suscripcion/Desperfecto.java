package models.entities.personas.colaborador.suscripcion;

import config.UtilsLocator;
import java.util.List;
import javax.persistence.Entity;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.personas.colaborador.Colaborador;
import models.searchers.BuscadorHeladerasFrecuentes;

/**
 * Clase que representa la notificacion referida
 * a un desperfecto ocurrido en una heladera.
 */

@Setter
@Entity
public class Desperfecto extends Suscripcion {
  
  /**
   * Constructor de la suscripcion en caso de que ocurran fallas.
   *
   * @param colaborador Suscriptor.
   * @param heladera Heladera a la que el suscriptor busca suscribirse.
   */

  public Desperfecto(Colaborador colaborador, Heladera heladera) {
    this.colaborador = colaborador;
    this.heladera = heladera;
    this.tipo = TipoSuscripcion.OCURRIO_DESPERFECTO;
  }

  public Desperfecto() {
    this.tipo = TipoSuscripcion.OCURRIO_DESPERFECTO;
  }

  @Override
  public Boolean seCumpleCondicion() {
    return !this.heladera.getEstadoActual().getEstado().equals(TipoEstado.ACTIVA);
  }

  @Override
  public String getAsunto() {
    return "La heladera " + this.heladera.getNombre() + " ha sufrido un desperfecto"
        + " y las viandas deben ser redistribuidas.";
  }

  @Override
  public String getCuerpo() {
    return "Estas son algunas de las heladeras a las que puedes llevar las viandas:\n"
        + this.nombresDeHeladerasString();
  }

  /**
   * Genera un string con los nombres de las heladeras frecuentes.
   *
   * @return El string generado.
   */

  public String nombresDeHeladerasString() {
    BuscadorHeladerasFrecuentes buscadorHeladerasFrecuentes =
        UtilsLocator.instanceOf(BuscadorHeladerasFrecuentes.class);
    List<Heladera> heladerasFrecuentes =
        buscadorHeladerasFrecuentes.heladerasFrecuentes(this.colaborador);
    StringBuilder s = new StringBuilder();
    for (Heladera heladera : heladerasFrecuentes) {
      s.append(heladera.getNombre()).append("\n");
    }
    return s.toString();
  }
}
