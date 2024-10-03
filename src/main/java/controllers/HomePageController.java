package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import models.entities.personas.users.TipoRol;
import org.jetbrains.annotations.NotNull;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de la vista de la página de inicio.
 */

public class HomePageController implements InterfaceCrudViewsHandler {
  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Heladeras Solidarias");

//    if (context.sessionAttribute("tipo_rol") == TipoRol.ADMINISTRADOR) {
//      context.render("home-page.hbs", model);
//    }

    context.render("home-page-admin.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

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
