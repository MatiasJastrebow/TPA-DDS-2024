package utils.recomendator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Clase molde para recibir el punto de la API Rest.
 */

@AllArgsConstructor
@Getter
public class Punto {
  String lat;
  String lng;
  String referencia;
}