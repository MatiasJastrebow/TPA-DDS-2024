package controllers;

import dtos.DireccionInputDto;
import dtos.HeladeraInputDto;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.sensores.SensorMovimiento;
import models.entities.heladera.sensores.SensorTemperatura;
import models.entities.personas.tecnico.VisitaTecnica;
import models.entities.personas.users.TipoRol;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.SensoresMovimientoRepository;
import models.repositories.imp.SensoresTemperaturaRepository;
import models.repositories.imp.VisitasTecnicasRepository;
import services.DireccionesService;
import services.HeladerasService;
import services.ReportesHeladerasService;
import utils.helpers.DateHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de heladeras para alta, baja y modificacion.
 */

public class HeladerasController implements InterfaceCrudViewsHandler {
  private final GenericRepository genericRepository;
  private final IncidentesRepository incidentesRepository;
  private final HeladerasService heladerasService;
  private final DireccionesService direccionesService;
  private final ReportesHeladerasService reportesHeladerasService;
  private final SensoresTemperaturaRepository sensoresTemperaturaRepository;
  private final SensoresMovimientoRepository sensoresMovimientoRepository;
  private  final VisitasTecnicasRepository visitasTecnicasRepository;

  /**
   * Constructor del Controller.
   *
   * @param genericRepository repositorio generico.
   * @param incidentesRepository repositorio de incidentes.
   * @param sensoresMovimientoRepository repositorio de sensores de movimiento.
   * @param sensoresTemperaturaRepository repositorio de sensores de temperatura.
   * @param visitasTecnicasRepository repositorio de visitas tecnicas.
   * @param heladerasService servicio de heladeras.
   * @param direccionesService servicio de direcciones.
   * @param reportesHeladerasService servicio de reportes de heladeras.
   */

  public HeladerasController(
      GenericRepository genericRepository,
      IncidentesRepository incidentesRepository,
      SensoresMovimientoRepository sensoresMovimientoRepository,
      SensoresTemperaturaRepository sensoresTemperaturaRepository,
      VisitasTecnicasRepository visitasTecnicasRepository,
      HeladerasService heladerasService,
      DireccionesService direccionesService,
      ReportesHeladerasService reportesHeladerasService
  ) {
    this.genericRepository = genericRepository;
    this.incidentesRepository = incidentesRepository;
    this.sensoresMovimientoRepository = sensoresMovimientoRepository;
    this.sensoresTemperaturaRepository = sensoresTemperaturaRepository;
    this.visitasTecnicasRepository = visitasTecnicasRepository;
    this.heladerasService = heladerasService;
    this.direccionesService = direccionesService;
    this.reportesHeladerasService = reportesHeladerasService;
  }

  /**
   * Muestra la lista de heladeras.
   *
   * @param context el contexto de la aplicación.
   */

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    model.put("titulo", "Heladeras");

    List<Heladera> heladeras = this.genericRepository.buscarTodos(Heladera.class);
    model.put("heladeras", heladeras);

    List<Provincia> provincias = this.genericRepository.buscarTodos(Provincia.class);
    model.put("provincias", provincias);

    List<Incidente> alertas = this.incidentesRepository.buscarTodos();
    model.put("alertas", alertas);

    List<Modelo> modelos = this.genericRepository.buscarTodos(Modelo.class);
    model.put("modelos", modelos);

    List<VisitaTecnica> visitas = this.visitasTecnicasRepository.buscarResueltos();
    model.put("visitas", visitas);

    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    if (TipoRol.valueOf(context.sessionAttribute("tipoRol")).equals(TipoRol.ADMINISTRADOR)) {
      context.render("heladeras-admin.hbs", model);
    } else {
      context.render("heladeras.hbs", model);
    }
  }

  public void show(Context context) {
  }

  public void create(Context context) {

  }

  /**
   * Guarda una heladera en la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void save(Context context) {
    HeladeraInputDto heladeraInputDto = HeladeraInputDto.fromContext(context);

    if (DateHelper.validate(heladeraInputDto.getFechaCreacion())) {
      context.redirect("/heladeras-solidarias/heladeras?invalidDate=true");
      return;
    }

    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);
    Direccion direccion = this.direccionesService.crear(direccionInputDto);

    Long idModelo = Long.parseLong(Objects.requireNonNull(context.formParam("modelo")));
    Modelo modelo = this.genericRepository.buscarPorId(idModelo, Modelo.class).get();

    Heladera heladera = this.heladerasService.crear(heladeraInputDto, direccion, modelo);

    this.reportesHeladerasService.crear(heladera);

    context.redirect("/heladeras-solidarias?actionSuccess=true");
  }

  public void edit(Context context) {

  }

  /**
   * Actualiza una heladera en la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void update(Context context) {
    HeladeraInputDto heladeraInputDto = HeladeraInputDto.fromContext(context);

    if (heladeraInputDto.getFechaCreacion().isAfter(LocalDate.now())) {
      context.redirect("/heladeras-solidarias/heladeras?invalidDate=true");
      return;
    }

    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    Long heladeraId = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));
    Heladera heladera = this.genericRepository.buscarPorId(heladeraId, Heladera.class).get();

    Long idModelo = Long.parseLong(Objects.requireNonNull(context.formParam("modelo")));
    Modelo modelo = this.genericRepository.buscarPorId(idModelo, Modelo.class).get();

    heladera.setModelo(modelo);

    if (direccionInputDto != null) {
      Direccion direccion = this.direccionesService.crear(direccionInputDto);
      heladera.setDireccion(direccion);
    }
    this.heladerasService.modificar(heladera, heladeraInputDto);

    context.redirect("/heladeras-solidarias?actionSuccess=true");
  }

  /**
   * Elimina una heladera de la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void delete(Context context) {
    Long heladeraId = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));

    Heladera heladera = this.genericRepository.buscarPorId(heladeraId, Heladera.class).get();

    SensorMovimiento sensorMovimiento =
        this.sensoresMovimientoRepository.buscarPorHeladera(heladera.getId()).get();
    SensorTemperatura sensorTemperatura =
        this.sensoresTemperaturaRepository.buscarPorHeladera(heladera.getId()).get();

    this.sensoresMovimientoRepository.eliminar(sensorMovimiento);
    this.sensoresTemperaturaRepository.eliminar(sensorTemperatura);

    this.genericRepository.eliminar(heladera);

    context.redirect("/heladeras-solidarias?actionSuccess=true");
  }
}
