package modules.domain.heladera;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.domain.direccion.Direccion;
import modules.domain.vianda.Vianda;

/**
 * Representa una heladera con una dirección, nombre, capacidad máxima de viandas, lista
 * de viandas y fecha de creación.
 */

@Getter
@Setter //(Modificacion de heladeras)
public class Heladera {
  private Direccion direccion;
  private String nombre;
  private Integer capacidadMaximaViandas;
  private List<Vianda> viandas;
  private Date fechaDeCreacion;
  private Modelo modelo;
  private SensorMovimiento sensorMovimiento;

  /**
   * Se inicializa la heladera (Dar de alta).
   *
   * @param direccion              es la direccion actual de la heladera.
   * @param nombre                 es el nombre de la heladera.
   * @param capacidadMaximaViandas es la capacidad maxima de viandas.
   * @param sensorMovimiento       es el sensor de movimiento propio de la heladera.
   * @param modelo                 es el modelo de la heladera.
   * @param fechaDeCreacion        fecha en la que se colocó la heladera.
   * @param viandas                es una lista con las viandas que hay dentro de la heladera.
   */

  public Heladera(Direccion direccion, String nombre,
                  Integer capacidadMaximaViandas, List<Vianda> viandas, Date fechaDeCreacion,
                  Modelo modelo, SensorMovimiento sensorMovimiento) {
    this.direccion = direccion;
    this.nombre = nombre;
    this.capacidadMaximaViandas = capacidadMaximaViandas;
    this.viandas = viandas;
    this.fechaDeCreacion = fechaDeCreacion;
    this.modelo = modelo;
    this.sensorMovimiento = sensorMovimiento;
  }

  //TODO No se si esto es asi (Mati)
  public void darHeladeraDeBaja() {
    //this = null;
  }

  /**
   * Se agrega una vianda a la heladera.
   *
   * @param vianda es la vianda que se busca agregar a la heladera.
   */

  public void agregarVianda(Vianda vianda) {
    if (this.tieneEspacio()) {
      this.viandas.add(vianda);
      vianda.setEntregada(true);
    } else {
      throw new RuntimeException("No hay mas espacio en la heladera");
    }
  }

  public Boolean estaActiva() {
    return !sensorMovimiento.estaActiva() && modelo.getSensorTemperatura().estaActiva();
  }

  //TODO
  public Float mesesActiva() {
    return 0f;
  }

  /**
   * Se elimina una vianda de la heladera.
   */

  public void removerVianda() {
    if (!this.viandas.isEmpty()) {
      viandas.remove(0);
    }
  }

  //==================================== Metodos auxiliares ========================================

  public Boolean tieneViandas() {
    return !this.viandas.isEmpty();
  }

  public Boolean tieneEspacio() {
    return this.capacidadMaximaViandas > this.viandas.size();
  }
}
