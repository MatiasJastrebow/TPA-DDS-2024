package models.entities.personas.colaborador;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;
import models.entities.colaboracion.Colaboracion;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.canje.Oferta;
import models.entities.personas.colaborador.reconocimiento.Reconocimiento;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.users.Usuario;

/**
 * Representa a un colaborador en el sistema.
 */

@Getter
@Setter
@Builder
@Entity
@Table(name = "colaboradores")
@AllArgsConstructor
public class Colaborador extends Persistente {

  @Transient
  private Usuario usuario;

  //Persona fisica
  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Embedded
  private Documento documento;

  //Persona Juridica
  @Column(name = "razonSocial")
  private String razonSocial;

  @Column(name = "tipo")
  private String tipo;

  @Column(name = "rubro")
  private String rubro;

  @OneToOne
  @JoinColumn(name = "direccion_id", referencedColumnName = "id")
  private Direccion direccion;

  //Ambos
  @Embedded
  private Contacto contacto;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoColaborador", nullable = false)
  private TipoColaborador tipoColaborador;

  @Embedded
  private Reconocimiento reconocimiento;

  @OneToMany(mappedBy = "colaborador", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<Colaboracion> colaboraciones;

  /**
   * Instancia un Colaborador.
   */

  public Colaborador() {
    this.reconocimiento = new Reconocimiento();
    this.colaboraciones = new ArrayList<>();
  }

  public Boolean puedeCanjear(Oferta oferta) {
    return reconocimiento.getPuntosPorColaborar() >= oferta.getPuntosNecesarios();
  }

  public void aumentarReconocimiento(Colaboracion colaboracion) {
    this.reconocimiento.sumarPuntos(colaboracion);
  }

  /**
   * Crea la solicitud para abrir una heladera.
   *
   * @param heladera Heladera que se busca abrir.
   */

  public void agregarSolicitudApertura(Heladera heladera, TarjetaColaborador tarjeta) {
    heladera.getTarjetasHabilitadas().add(tarjeta);
  }

  public void agregarColaboracion(Colaboracion colaboracion) {
    this.colaboraciones.add(colaboracion);
    colaboracion.setColaborador(this);
  }

}

