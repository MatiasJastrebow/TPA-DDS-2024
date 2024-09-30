package controllers;

import dtos.VulnerableInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
import services.DireccionesService;
import services.VulnerablesService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para el registro de vulnerables.
 */

public class VulnerablesController implements InterfaceCrudViewsHandler {

  private final GenericRepository vulnerablesRepository;
  private final VulnerablesService vulnerablesService;

  public VulnerablesController(
      GenericRepository vulnerablesRepository,
      VulnerablesService vulnerablesService
  ) {
    this.vulnerablesRepository = vulnerablesRepository;
    this.vulnerablesService = vulnerablesService;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Registrar Vulnerable");
    context.render("/registrar-vulnerable.hbs");
  }

  @Override
  public void save(Context context) {
    VulnerableInputDto vulnerableInputDto = new VulnerableInputDto(
        context.formParam("nombre"),
        context.formParam("fechaNacimiento"),
        context.formParam("tipoDocumento"),
        context.formParam("numeroDocumento"),
        context.formParam("provincia"),
        context.formParam("ciudad"),
        context.formParam("barrio"),
        context.formParam("calle"),
        context.formParam("numero"),
        context.formParam("cantMenores")
    );

    Vulnerable vulnerable = this.vulnerablesService.crear(vulnerableInputDto);

    //TODO LOGICA DE MENORES A CARGO

    this.vulnerablesRepository.guardar(vulnerable);

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
