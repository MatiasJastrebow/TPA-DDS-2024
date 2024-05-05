package modules.domain.vianda;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una comida con un nombre y una fecha de vencimiento.
 */


@Getter
@Setter
public class Comida {
  private String nombre;
  private Date vencimiento;
}
