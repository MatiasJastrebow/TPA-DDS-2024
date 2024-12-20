package controllers;

import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.canje.Canje;
import models.entities.personas.colaborador.canje.Oferta;
import models.repositories.imp.CanjesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import services.CanjearPuntosService;
import utils.helpers.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para canjear los puntos.
 */

public class CanjearPuntosController implements InterfaceCrudViewsHandler {

  private final GenericRepository ofertasRepository;
  private final CanjesRepository canjesRepository;
  private final ColaboradoresRepository colaboradoresRepository;
  private final CanjearPuntosService canjearPuntosService;

  /**
   * Constructor del controller de canjear puntos.
   *
   * @param ofertasRepository       el repositorio de ofertas.
   * @param colaboradoresRepository el repositorio de colaboradores.
   * @param canjearPuntosService    el servicio de canjear puntos.
   */

  public CanjearPuntosController(
      GenericRepository ofertasRepository,
      CanjesRepository canjesRepository,
      ColaboradoresRepository colaboradoresRepository,
      CanjearPuntosService canjearPuntosService
  ) {
    this.ofertasRepository = ofertasRepository;
    this.canjesRepository = canjesRepository;
    this.colaboradoresRepository = colaboradoresRepository;
    this.canjearPuntosService = canjearPuntosService;
  }

  /**
   * Muestra las ofertas.
   *
   * @param context el contexto de la aplicación.
   */

  public void index(Context context) {
    List<Oferta> ofertas = ofertasRepository.buscarTodos(Oferta.class);

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Ofertas");
    model.put("ofertas", ofertas);

    Long idSesion = context.sessionAttribute("idUsuario");
    if (idSesion != null) {
      model.put("activeSession", true);
      model.put("tipoRol", context.sessionAttribute("tipoRol"));

      Optional<Colaborador> colaborador = colaboradoresRepository.buscarPorIdUsuario(idSesion);
      List<Canje> canjesRealizados = new ArrayList<>();

      if (colaborador.isPresent()) {
        model.put("puntos", colaborador.get().getReconocimiento().getPuntosPorColaborar());
        canjesRealizados = canjesRepository.buscarCanjesDe(colaborador.get().getId());
        model.put("ofertasCanjeadas", canjesRealizados);
      } else {
        model.put("puntos", 0f);
      }

    } else {
      model.put("activeSession", false);
      context.redirect("/heladeras-solidarias");
      return;
    }

    context.render("canjear-puntos.hbs", model);
  }

  public void show(Context context) {

  }

  public void create(Context context) {
  }

  public void save(Context context) {

  }

  public void edit(Context context) {

  }

  /**
   * metodo para canjear una oferta por puntos.
   *
   * @param context el contexto de quien realiza el post.
   */

  public void update(Context context) {
    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Long idOferta = Long.valueOf(Objects.requireNonNull(context.formParam("idOferta")));
    Oferta oferta = this.ofertasRepository.buscarPorId(idOferta, Oferta.class).get();

    if (colaborador.puedeCanjear(oferta)) {
      this.canjearPuntosService.crear(oferta, colaborador);
      context.redirect("/heladeras-solidarias?trade=true");
    } else {
      context.redirect("/heladeras-solidarias/canjear-puntos?insufficientFunds=true");
    }
  }

  public void delete(Context context) {

  }
}
