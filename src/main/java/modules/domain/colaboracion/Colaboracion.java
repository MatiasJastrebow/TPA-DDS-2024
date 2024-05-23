package modules.domain.colaboracion;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.domain.heladera.Heladera;
import modules.domain.personas.colaborador.Oferta;
import modules.domain.tarjeta.Tarjeta;
import modules.domain.vianda.Vianda;

/**
 * Representa una colaboracion dentro del sistema.
 */

@Getter
@Setter
public class Colaboracion {
  private TipoColaboracion tipoColaboracion;

  //Donar dinero
  private LocalDate fechaDonacion;
  private Integer monto;
  private String frecuencia;

  //Donar vianda
  private List<Vianda> viandas;

  //Distribuir vianda
  private Heladera heladeraOrigen;
  private Heladera heladeraDestino;
  private Integer cantViandasDistribuidas;
  private String motivoDistribucion;
  private LocalDate fechaDistribucion;

  //Colocar heladera
  private Heladera heladera;

  //Distribuir tarjetas
  private HashSet<Tarjeta> tarjetasEntregadas;

  //Realizar ofertas
  private List<Oferta> ofertas;

  public Colaboracion() {}
  //Se crea sin datos y se agregan los necesarios con setters

  //============================== Metodos Auxiliares ========================================

  public Integer tiempoActivaHeladera() {
    return this.heladera.calcularMesesActiva();
  }

  public Integer cantViandasDonadas() {
    return this.viandas.size();
  }

  public Integer cantTarjetasEntregadas() {
    return this.tarjetasEntregadas.size();
  }
}
