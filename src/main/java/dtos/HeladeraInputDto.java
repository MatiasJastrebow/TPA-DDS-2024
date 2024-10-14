package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import models.entities.heladera.Heladera;

/**
 * DTO para la creación de una heladera.
 */

@Data
@AllArgsConstructor
@Builder
public class HeladeraInputDto {
  String nombre;
  String fechaCreacion;
  String capacidadMaximaViandas;

  /**
   * Crea un HeladeraInputDto a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return un HeladeraInputDto.
   */

  public static HeladeraInputDto fromContext(Context context) {
    return HeladeraInputDto.builder()
        .nombre(context.formParam("nombre"))
        .fechaCreacion(context.formParam("fechaCreacion"))
        .capacidadMaximaViandas(context.formParam("capacidadMaximaViandas"))
        .build();
  }
}
