package controllers;

import dtos.OfertaDto;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.entities.personas.colaborador.canje.Oferta;
import models.repositories.imp.GenericRepository;
import org.apache.commons.io.FileUtils;
import services.OfertasService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para canjear los puntos.
 */

public class CanjearPuntosController implements InterfaceCrudViewsHandler {

  private final GenericRepository ofertasRepository;
  private final OfertasService ofertasService;

  public CanjearPuntosController(GenericRepository repo, OfertasService ofertasService) {
    this.ofertasRepository = repo;
    this.ofertasService = ofertasService;
  }

  @Override
  public void index(Context context) {
    List<Oferta> ofertas = ofertasRepository.buscarTodos(Oferta.class);

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Ofertas");
    model.put("ofertas", ofertas);

    context.render("canjear-puntos.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Ofertas");

    context.render("colaborar.hbs", model);
  }

  //TODO esto va en la pagina de colaborar cuando se cree
  @Override
  public void save(Context context) {

    // TODO sacar al finalizar
    // para ver que se esta trayendo
    context.formParamMap().forEach((key, value) -> System.out.println(key + " " + value));

    OfertaDto nuevaOferta = new OfertaDto();
    nuevaOferta.setNombre(context.formParam("nombre"));
    nuevaOferta.setDescripcion(context.formParam("descripcion"));
    nuevaOferta.setPuntosNecesarios(context.formParam("puntosNecesarios"));
    nuevaOferta.setOfertante("4");
    //nuevaOferta.setOfertante(context.sessionAttribute("idUsuario"));

    UploadedFile file = context.uploadedFile("imagen");
    if (file != null) {
      String path = "/public/uploaded-imgs/" + file.filename();
      try {
        FileUtils.copyInputStreamToFile(file.content(),
            new File(path));
        nuevaOferta.setImagenIlustrativa(path);
      } catch (IOException e) {
        nuevaOferta.setImagenIlustrativa("/public/static-imgs/logo.png");
      }
    } else {
      nuevaOferta.setImagenIlustrativa("/public/static-imgs/logo.png");
    }

    try {
      this.ofertasRepository.guardar(this.ofertasService.crear(nuevaOferta));
    } catch (Exception e) {
      context.status(500).result("Error al guardar la oferta: " + e.getMessage());
    }

    //O BIEN LANZO UNA PANTALLA DE EXITO
    //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS

    context.redirect("/heladeras-solidarias/canjear-puntos");
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    Optional<Oferta> posibleCanjeBuscado = this
        .ofertasRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Oferta.class);

    posibleCanjeBuscado.ifPresent(ofertasRepository::modificar);
  }

  @Override
  public void delete(Context context) {
    Optional<Oferta> posibleCanjeBuscado = this
        .ofertasRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Oferta.class);

    posibleCanjeBuscado.ifPresent(ofertasRepository::eliminar);
  }
}
