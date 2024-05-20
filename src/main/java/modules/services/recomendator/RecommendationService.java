package modules.services.recomendator;

import modules.services.recomendator.entities.ListadoDepuntos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * La API que provee la consultora para la selección de puntos.
 */
public interface ServiceRecommendation {
  @GET("puntos")
  Call<ListadoDepuntos> municipios(@Query("latitud") int latitud,@Query("longitud") int longitud);

}
