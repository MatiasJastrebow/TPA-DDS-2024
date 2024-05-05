package modules.domain.tarjeta;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import modules.domain.heladera.Heladera;

/**
 * Representa un registro de uso con una fecha de utilización y una heladera.
 */

@Getter
@Setter
public class RegistroUso {
  private Date fechaUtilizacion;
  private Heladera heladera;
}
