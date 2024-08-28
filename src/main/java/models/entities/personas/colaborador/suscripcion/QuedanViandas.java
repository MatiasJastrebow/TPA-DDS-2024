package models.entities.personas.colaborador.suscripcion;

import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;
import utils.sender.SenderLocator;

/**
 * Clase que representa la notificacion referida
 * a cuantas viandas quedan en una heladera.
 */

@Setter
public class QuedanViandas implements InterfazSuscripcion {
  private Colaborador colaborador;
  private Heladera heladera;
  private Integer viandasDisponibles;
  private SenderInterface senderInterface;

  /**
   * Instancia una suscripcion.
   *
   * @param colaborador Suscriptor.
   * @param heladera Heladera a la que el suscriptor busca suscribirse.
   * @param viandas Cantidad de viandas para ser notificado.
   */

  public QuedanViandas(Colaborador colaborador, Heladera heladera, Integer viandas) {
    this.colaborador = colaborador;
    this.heladera = heladera;
    this.viandasDisponibles = viandas;
    this.senderInterface =
        SenderLocator.getService(colaborador.getContacto().getTipoContacto());
  }

  /**
   * Intenta notificar si es necesario.
   */

  public void intentarNotificar() {
    if (this.heladera.consultarStock().equals(this.viandasDisponibles)) {
      this.notificar();
    }
  }

  /**
   * Enviar notificacion al colaborador.
   */

  public void notificar() {
    String destinatario = colaborador.getContacto().getInfo();
    String asunto = "Quedan sólo " + viandasDisponibles + " en una heladera.";
    String cuerpo = "Acercate a la heladera: " + heladera.getNombre() + " para rellenarla";
    Mensaje mensaje = new Mensaje(asunto, cuerpo);
    this.senderInterface.enviar(mensaje, destinatario);
  }
}
