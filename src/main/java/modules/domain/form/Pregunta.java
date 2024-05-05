package modules.domain.form;

import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;

/**
 * Representa una pregunta de un formulario.
 * Tiene la pregunta en si misma, la opcionalidad, las opciones y el tipo de la respuesta.
 */

@Getter
@Setter
public class Pregunta {
  private String pregunta;
  private Boolean esOpcional;
  private HashSet<Opcion> opciones;
  private TipoPregunta tipoDeSuRespuesta;
}
