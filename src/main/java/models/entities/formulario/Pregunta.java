package models.entities.formulario;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa una pregunta de un formulario.
 * Tiene la pregunta en sí misma, la opcionalidad, las opciones y el tipo de la respuesta.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "preguntas")
public class Pregunta extends Persistente {

  @Column(name = "pregunta", nullable = false)
  private String pregunta;

  @Column(name = "esObligatoria", nullable = false)
  private Boolean obligatoria = false;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch =  FetchType.EAGER)
  @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
  private List<Opcion> opciones;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo", nullable = false)
  private TipoPregunta tipoPregunta;

  public void agregarOpcion(Opcion opcion) {
    this.opciones.add(opcion);
  }
}
