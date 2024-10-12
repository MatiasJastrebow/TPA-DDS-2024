package controllers.colaboraciones;

import io.javalin.http.Context;
import java.util.Objects;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import services.ColaboracionesService;
import utils.ColaboracionesHelper;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para la distribucion de tarjetas.
 */

//TODO
public class DistribuirTarjetasController implements InterfaceCrudViewsHandler {

  private final ColaboracionesService colaboracionesService;

  public DistribuirTarjetasController(
      ColaboracionesService colaboracionesService
  ) {
    this.colaboracionesService = colaboracionesService;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {
    Integer cantidadTarjetas =
        Integer.parseInt(Objects.requireNonNull(context.formParam("cant_tarjetas")));

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    if (!colaborador.getDireccion().admiteEnvio()) {
      //TODO MANEJAR ERROR
      context.redirect("/heladeras-solidarias/agregar-direccion");
      return;
    }

    Colaboracion colaboracion = this.colaboracionesService.crear(cantidadTarjetas);

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

    context.redirect("/heladeras-solidarias");
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}