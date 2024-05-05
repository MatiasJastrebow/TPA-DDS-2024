package modules.domain.personas.tecnico;

import lombok.Getter;
import lombok.Setter;
import modules.domain.direccion.Provincia;
import modules.domain.personas.TipoDocumento;

/**
 * Representa un técnico con nombre, apellido, tipo de documento,
 * número de documento, CUIL, medio de contacto y área de cobertura.
 */

@Getter
@Setter
public class Tecnico {
  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private Integer numeroDeDocumento;
  private Integer cuil;
  private String medioContacto; //TODO revisar si queremos enum
  private Provincia areaDeCobertura; //TODO revisar si queremos otro tipo
}
