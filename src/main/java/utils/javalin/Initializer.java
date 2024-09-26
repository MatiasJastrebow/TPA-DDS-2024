package utils.javalin;

import config.RepositoryLocator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import models.db.PersistenceUnitSwitcher;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.ColocacionHeladera;
import models.entities.colaboracion.DistribucionTarjetas;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.RealizacionOfertas;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.direccion.Barrio;
import models.entities.direccion.Ciudad;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.formulario.Formulario;
import models.entities.formulario.Opcion;
import models.entities.formulario.Pregunta;
import models.entities.formulario.Respuesta;
import models.entities.formulario.RespuestaFormulario;
import models.entities.formulario.TipoPregunta;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.entities.heladera.estados.Estado;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;
import models.entities.heladera.vianda.Comida;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.colaborador.canje.Oferta;
import models.entities.personas.colaborador.reconocimiento.Reconocimiento;
import models.entities.personas.colaborador.reconocimiento.formula.imp.Formula;
import models.entities.personas.colaborador.suscripcion.Desperfecto;
import models.entities.personas.colaborador.suscripcion.FaltanViandas;
import models.entities.personas.colaborador.suscripcion.QuedanViandas;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.contacto.TipoContacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.tecnico.Tecnico;
import models.entities.personas.vulnerable.Vulnerable;
import models.entities.reporte.ReporteHeladera;
import models.entities.reporte.ViandasPorColaborador;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ProvinciasRepository;
import models.repositories.imp.ReportesRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import utils.sender.channels.EmailSender;

/**
 * Clase que inicializa la aplicación con datos de prueba.
 */

public class Initializer {

  @Getter
  static ColaboradoresRepository colaboradoresRepository;

  @Getter
  static ColaboracionesRepository colaboracionesRepository;

  static UsosTarjetasVulnerablesRepository usosTarjetasVulnerablesRepository;

  @Getter
  static TecnicosRepository tecnicosRepository;

  @Getter
  static GenericRepository repoGenerico;

  static ReportesRepository reportesRepository;

  static DireccionesRepository direccionesRepository;

  static ProvinciasRepository provinciasRepository;

  static ColocacionHeladera colocacionHeladera;
  @Getter
  static Colaboracion colocarHeladera;

  static DistribucionTarjetas distribucionTarjetas;
  static TarjetaVulnerable tarjeta1;
  static TarjetaVulnerable tarjeta2;
  static TarjetaVulnerable tarjeta3;
  static List<TarjetaVulnerable> lstTarjetas;
  static Colaboracion distribuirTarjetas;

  static DistribucionViandas distribucionViandas;
  static Colaboracion distribuirViandas;

  static DonacionDinero donacionDinero;
  static Colaboracion donarDinero;

  static RealizacionOfertas realizarOfertas;
  static List<Oferta> lstOfertas;
  static Oferta oferta1;
  static Oferta oferta2;
  static Oferta oferta3;
  static Colaboracion realizarOferta;

  @Getter
  static Direccion direccion1;
  static Direccion direccion2;
  static Direccion direccion3;

  static Barrio barrio1;
  static Barrio barrio2;
  static Barrio barrio3;

  static Ciudad ciudad1;
  static Ciudad ciudad2;
  static Ciudad ciudad3;

  @Getter
  static Provincia provincia1;
  static Provincia provincia2;
  static Provincia provincia3;

  static Formulario formulario1;

  static List<Pregunta> lstPreguntas1;

  static Pregunta pregunta1;
  static Pregunta pregunta2;
  static Pregunta pregunta3;

  static List<Opcion> lstOpciones1;
  static List<Opcion> lstOpciones2;
  static List<Opcion> lstOpciones3;

  static Opcion opcion1;
  static Opcion opcion2;
  static Opcion opcion3;
  static Opcion opcion4;
  static Opcion opcion5;
  static Opcion opcion6;
  static Opcion opcion7;
  static Opcion opcion8;
  static Opcion opcion9;

  static Respuesta respuesta1;
  static Respuesta respuesta2;
  static Respuesta respuesta3;

  static RespuestaFormulario respuestaFormulario1;
  static RespuestaFormulario respuestaFormulario2;
  static RespuestaFormulario respuestaFormulario3;

  @Getter
  static Heladera heladera1;
  static Heladera heladera2;
  static Heladera heladera3;

  static Estado estadoActual1;
  static Estado estadoActual2;
  static Estado estadoActual3;

  static List<Estado> lstEstados1;
  static List<Estado> lstEstados2;
  static List<Estado> lstEstados3;

  static Estado estado1;
  static Estado estado2;
  static Estado estado3;
  static Estado estado4;
  static Estado estado5;

  static SensorMovimiento sensorMovimiento1;
  static SensorMovimiento sensorMovimiento2;
  static SensorMovimiento sensorMovimiento3;

  static SensorTemperatura sensorTemperatura1;
  static SensorTemperatura sensorTemperatura2;
  static SensorTemperatura sensorTemperatura3;

  static List<Vianda> lstViandas1;
  static List<Vianda> lstViandas2;
  static List<Vianda> lstViandas3;

  static Vianda vianda1;
  static Vianda vianda2;
  static Vianda vianda3;
  static Vianda vianda4;
  static Vianda vianda5;
  static Vianda vianda6;
  static Vianda vianda7;
  static Vianda vianda8;
  static Vianda vianda9;

  static Comida comida1;
  static Comida comida2;
  static Comida comida3;
  static Comida comida4;
  static Comida comida5;
  static Comida comida6;
  static Comida comida7;
  static Comida comida8;
  static Comida comida9;

  static Modelo modelo1;
  static Modelo modelo2;
  static Modelo modelo3;

  static Documento documentoAugusto;

  static Formula formula;

  static Reconocimiento reconocimientoAugusto;
  static Reconocimiento reconocimientoIniaqui;
  static Reconocimiento reconocimientoMati;

  static Contacto contactoAugusto;
  static Contacto contactoIniaqui;
  static Contacto contactoMati;
  static Contacto contactoCitiGroup;
  static Contacto contactoLiam;
  static Contacto contactoSanti;

  @Getter
  static Colaborador augusto;
  static Colaborador iniaki;
  static Colaborador mati;
  static Colaborador elCityGroup;

  static Desperfecto desperfecto;
  static FaltanViandas faltanViandas;
  static QuedanViandas quedanViandas;

  @Getter
  static Tecnico liam;
  static Tecnico santi;

  @Getter
  static Vulnerable eze;
  static Vulnerable facu;

  // Vulnerables a cargo de eze
  static List<Vulnerable> lstMenoresACargoEze;
  static Vulnerable perez;
  static Vulnerable tello;
  static Vulnerable enrique;

  // Vulnerables a cargo de facu
  static List<Vulnerable> lstMenoresACargoFacu;
  static Vulnerable villalva;

  static RegistroVulnerable registroVulnerable1;
  static RegistroVulnerable registroVulnerable2;
  static RegistroVulnerable registroVulnerable3;

  static ReporteHeladera reporteHeladera1;
  static ReporteHeladera reporteHeladera2;
  static ReporteHeladera reporteHeladera3;

  static ViandasPorColaborador viandasPorColaborador1;
  static ViandasPorColaborador viandasPorColaborador2;
  static ViandasPorColaborador viandasPorColaborador3;

  /**
   * Inicializa la aplicación con datos de prueba.
   */

  public static void init(String unidadPersistencia) {
    //Para testear en db poner database en  vez de simple
    PersistenceUnitSwitcher.switchPersistenceUnit(unidadPersistencia);

    iniciarContactos();
    iniciarColaboradores();
    iniciarVulnerables();
    iniciarProvincias();
    iniciarCiudades();
    iniciarBarrios();
    iniciarDirecciones();
    iniciarEstados();
    iniciarModelos();
    iniciarSensores();
    iniciarHeladeras();
    iniciarViandas();
    iniciarRegistrosVulnerables();
    iniciarTarjetas();
    iniciarOfertas();
    iniciarColaboraciones();
    iniciarTecnicos();
    iniciarFormularios();
    iniciarSuscripciones();
    iniciarViandasPorColaborador();
    iniciarReportes();

    persistirEntidades();
  }

  private static void persistirEntidades() {
    iniciarRepos();

    repoGenerico.guardar(direccion1);
    repoGenerico.guardar(direccion2);
    repoGenerico.guardar(direccion3);

    colaboradoresRepository.guardar(augusto);
    colaboradoresRepository.guardar(iniaki);
    colaboradoresRepository.guardar(mati);
    colaboradoresRepository.guardar(elCityGroup);

    repoGenerico.guardar(heladera1);
    repoGenerico.guardar(heladera2);
    repoGenerico.guardar(heladera3);

    repoGenerico.guardar(villalva);
    repoGenerico.guardar(enrique);
    repoGenerico.guardar(perez);
    repoGenerico.guardar(tello);
    repoGenerico.guardar(eze);
    repoGenerico.guardar(facu);

    repoGenerico.guardar(desperfecto);
    repoGenerico.guardar(faltanViandas);
    repoGenerico.guardar(quedanViandas);

    repoGenerico.guardar(registroVulnerable1);
    repoGenerico.guardar(registroVulnerable2);
    repoGenerico.guardar(registroVulnerable3);

    colaboracionesRepository.guardar(colocarHeladera);
    colaboracionesRepository.guardar(distribuirTarjetas);
    colaboracionesRepository.guardar(distribuirViandas);
    colaboracionesRepository.guardar(donarDinero);
    colaboracionesRepository.guardar(realizarOferta);

    tecnicosRepository.guardar(liam);
    tecnicosRepository.guardar(santi);

    repoGenerico.guardar(formulario1);

    repoGenerico.guardar(viandasPorColaborador1);
    repoGenerico.guardar(viandasPorColaborador2);
    repoGenerico.guardar(viandasPorColaborador3);

    reportesRepository.guardar(reporteHeladera1);
    reportesRepository.guardar(reporteHeladera2);
    reportesRepository.guardar(reporteHeladera3);

  }

  static void iniciarContactos() {
    contactoAugusto = new Contacto("+54 9 11 1234-5678", TipoContacto.WHATSAPP);
    contactoIniaqui = new Contacto("54645213212", TipoContacto.TELEGRAM);
    contactoMati = new Contacto("contactofalso@gmail.com", TipoContacto.MAIL);
    contactoCitiGroup = new Contacto("citigroup@hdp.com", TipoContacto.MAIL);
    contactoLiam = new Contacto("liam@gmail.com", TipoContacto.MAIL);
    contactoSanti = new Contacto("santi@ghost.com", TipoContacto.MAIL);
  }

  static void iniciarColaboradores() {

    formula = new Formula();

    reconocimientoAugusto = new Reconocimiento();
    reconocimientoAugusto.setFormulaCalculoDePuntos(formula);

    reconocimientoIniaqui = new Reconocimiento();
    reconocimientoIniaqui.setFormulaCalculoDePuntos(formula);

    reconocimientoMati = new Reconocimiento();
    reconocimientoMati.setFormulaCalculoDePuntos(formula);

    documentoAugusto = new Documento(45345678, TipoDocumento.DNI);

    augusto = new Colaborador();
    augusto.setNombre("Augusto");
    augusto.setApellido("Mazzini");
    augusto.setDocumento(documentoAugusto);
    augusto.setContacto(contactoAugusto);
    augusto.setTipoColaborador(TipoColaborador.FISICO);
    augusto.setReconocimiento(reconocimientoAugusto);

    iniaki = new Colaborador();
    iniaki.setNombre("Iñaki");
    iniaki.setApellido("Lorences");
    iniaki.setContacto(contactoIniaqui);
    iniaki.setTipoColaborador(TipoColaborador.FISICO);
    iniaki.setReconocimiento(reconocimientoIniaqui);

    mati = new Colaborador();
    mati.setNombre("Matias");
    mati.setApellido("Jastrebow");
    mati.setContacto(contactoMati);
    mati.setTipoColaborador(TipoColaborador.FISICO);
    mati.setReconocimiento(reconocimientoMati);

    elCityGroup = new Colaborador();
    elCityGroup.setNombre("CityGroup");
    elCityGroup.setRazonSocial("El City Group PAPA.SA");
    elCityGroup.setTipoColaborador(TipoColaborador.EMPRESA_ASOCIADA);
    elCityGroup.setContacto(contactoCitiGroup);
  }

  static void iniciarVulnerables() {

    lstMenoresACargoEze = new ArrayList<>();
    lstMenoresACargoFacu = new ArrayList<>();

    perez = new Vulnerable();
    perez.setNombre("Falcon Perez");
    perez.setFechaNacimiento(LocalDate.of(1972, 4, 22));

    tello = new Vulnerable();
    tello.setNombre("Facundo Tello");
    tello.setFechaNacimiento(LocalDate.of(1982, 5, 3));

    villalva = new Vulnerable();
    villalva.setNombre("El keko Villalva");
    villalva.setFechaNacimiento(LocalDate.of(1975, 6, 4));

    enrique = new Vulnerable();
    enrique.setNombre("Enrique Pinti");
    enrique.setFechaNacimiento(LocalDate.of(1999, 7, 5));

    lstMenoresACargoEze.add(perez);
    lstMenoresACargoEze.add(tello);
    lstMenoresACargoEze.add(enrique);

    lstMenoresACargoFacu.add(villalva);

    eze = new Vulnerable();
    eze.setNombre("Ezequiel");
    eze.setFechaNacimiento(LocalDate.of(1994, 8, 31));
    eze.setMenoresAcargo(lstMenoresACargoEze);

    facu = new Vulnerable();
    facu.setNombre("Facundo");
    facu.setFechaNacimiento(LocalDate.of(2001, 7, 30));
    facu.setMenoresAcargo(lstMenoresACargoFacu);
  }

  static void iniciarColaboraciones() {

    // Colaboracion - Colocar Heladera

    colocacionHeladera = new ColocacionHeladera();
    colocacionHeladera.setHeladeraColocada(heladera2);

    colocarHeladera = new Colaboracion();
    colocarHeladera.setFechaColaboracion(LocalDate.of(2021, 10, 10));
    colocarHeladera.setColocacionHeladera(colocacionHeladera);
    colocarHeladera.setTipoColaboracion(TipoColaboracion.COLOCAR_HELADERA);
    colocarHeladera.setColaborador(augusto);

    // Colaboracion - Distribuir Tarjetas

    distribucionTarjetas = new DistribucionTarjetas();
    distribucionTarjetas.setCantTarjetasEntregadas(3);
    distribucionTarjetas.setTarjetasEntregadas(lstTarjetas);

    distribuirTarjetas = new Colaboracion();
    distribuirTarjetas.setColaborador(iniaki);
    distribuirTarjetas.setTipoColaboracion(TipoColaboracion.ENTREGAR_TARJETA);
    distribuirTarjetas.setDistribucionTarjetas(distribucionTarjetas);
    distribuirTarjetas.setFechaColaboracion(LocalDate.of(2023, 10, 28));

    // Colaboracion - Distribuir Viandas

    distribucionViandas = new DistribucionViandas();
    distribucionViandas.setMotivoDistribucion("Habia hambre");
    distribucionViandas.setCantViandasDistribuidas(3);
    distribucionViandas.setHeladeraOrigen(heladera1);
    distribucionViandas.setHeladeraDestino(heladera3);

    distribuirViandas = new Colaboracion();
    distribuirViandas.setColaborador(mati);
    distribuirViandas.setTipoColaboracion(TipoColaboracion.DISTRIBUIR_VIANDAS);
    distribuirViandas.setDistribucionViandas(distribucionViandas);
    distribuirViandas.setFechaColaboracion(LocalDate.of(2022, 1, 10));

    // Colaboracion - Donar Dinero

    donacionDinero = new DonacionDinero();
    donacionDinero.setMontoDonado(150000);
    donacionDinero.setFrecuenciaDonacion("Casi nunca, tampoco es millonario");

    donarDinero = new Colaboracion();
    donarDinero.setFechaColaboracion(LocalDate.of(2011, 6, 26));
    donarDinero.setTipoColaboracion(TipoColaboracion.DONAR_DINERO);
    donarDinero.setColaborador(iniaki);
    donarDinero.setDonacionDinero(donacionDinero);


    // Les asigno doy los puntos por las colaboraciones

    augusto.getReconocimiento().sumarPuntos(colocarHeladera);
    iniaki.getReconocimiento().sumarPuntos(distribuirTarjetas);
    mati.getReconocimiento().sumarPuntos(distribuirViandas);
    iniaki.getReconocimiento().sumarPuntos(donarDinero);


    // Colaboracion - Realizar Ofertas

    realizarOfertas = new RealizacionOfertas();
    realizarOfertas.setOfertasRealizadas(lstOfertas);

    realizarOferta = new Colaboracion();
    realizarOferta.setColaborador(elCityGroup);
    realizarOferta.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);
    realizarOferta.setFechaColaboracion(LocalDate.of(2012, 7, 27));
    realizarOferta.setRealizarOfertas(realizarOfertas);
  }

  static void iniciarTarjetas() {

    lstTarjetas = new ArrayList<>();

    tarjeta1 = new TarjetaVulnerable(registroVulnerable1);

    tarjeta2 = new TarjetaVulnerable(registroVulnerable2);

    tarjeta3 = new TarjetaVulnerable(registroVulnerable3);

    lstTarjetas.add(tarjeta1);
    lstTarjetas.add(tarjeta2);
    lstTarjetas.add(tarjeta3);
  }

  static void iniciarRegistrosVulnerables() {

    registroVulnerable1 = new RegistroVulnerable();
    registroVulnerable1.setFechaRegistro(LocalDate.of(2009, 1, 22));
    registroVulnerable1.setColaborador(iniaki);
    registroVulnerable1.setFechaAlta(LocalDate.of(2009, 7, 15));
    registroVulnerable1.setVulnerable(facu);

    registroVulnerable2 = new RegistroVulnerable();
    registroVulnerable2.setFechaRegistro(LocalDate.of(2010, 2, 23));
    registroVulnerable2.setColaborador(iniaki);
    registroVulnerable2.setFechaAlta(LocalDate.of(2010, 8, 16));
    registroVulnerable2.setVulnerable(eze);

    registroVulnerable3 = new RegistroVulnerable();
    registroVulnerable3.setFechaRegistro(LocalDate.of(2011, 3, 24));
    registroVulnerable3.setColaborador(iniaki);
    registroVulnerable3.setFechaAlta(LocalDate.of(2011, 9, 17));
    registroVulnerable3.setVulnerable(enrique);
  }

  static void iniciarHeladeras() {

    heladera1 = new Heladera();
    heladera1.setFechaDeCreacion(LocalDate.now());
    heladera1.setEstadoActual(estadoActual1);
    heladera1.setDireccion(direccion1);
    heladera1.setNombre("Heladera Porteña");
    heladera1.setEstadosHeladera(lstEstados1);
    heladera1.setModelo(modelo1);
    heladera1.setCapacidadMaximaViandas(18);

    heladera2 = new Heladera();
    heladera2.setFechaDeCreacion(LocalDate.now());
    heladera2.setEstadoActual(estadoActual2);
    heladera2.setDireccion(direccion2);
    heladera2.setNombre("Heladera Cordobesa");
    heladera2.setEstadosHeladera(lstEstados2);
    heladera2.setModelo(modelo2);
    heladera2.setCapacidadMaximaViandas(25);

    heladera3 = new Heladera();
    heladera3.setFechaDeCreacion(LocalDate.now());
    heladera3.setEstadoActual(estadoActual3);
    heladera3.setDireccion(direccion3);
    heladera3.setNombre("Heladera Santiagueña");
    heladera3.setEstadosHeladera(lstEstados3);
    heladera3.setModelo(modelo3);
    heladera3.setCapacidadMaximaViandas(4);
  }

  static void iniciarViandas() {
    lstViandas1 = new ArrayList<>();
    lstViandas2 = new ArrayList<>();
    lstViandas3 = new ArrayList<>();

    comida1 = new Comida("Hamburguesa", LocalDate.of(2024, 9, 3));
    comida2 = new Comida("Ensalada", LocalDate.of(2024, 10, 3));
    comida3 = new Comida("Milanesa", LocalDate.of(2024, 11, 3));
    comida4 = new Comida("Pizza", LocalDate.of(2024, 12, 3));
    comida5 = new Comida("Sushi", LocalDate.of(2024, 1, 3));
    comida6 = new Comida("Tacos", LocalDate.of(2024, 2, 3));
    comida7 = new Comida("Lasagna", LocalDate.of(2024, 3, 3));
    comida8 = new Comida("Empanadas", LocalDate.of(2024, 4, 3));
    comida9 = new Comida("Paella", LocalDate.of(2024, 5, 3));

    vianda1 = new Vianda(comida1,
        LocalDate.of(2024, 9, 10),
        mati,
        heladera1,
        480,
        270.5F,
        false
    );

    vianda2 = new Vianda(comida2,
        LocalDate.of(2024, 9, 18),
        mati,
        heladera1,
        350,
        220.0F,
        false
    );

    vianda3 = new Vianda(comida3,
        LocalDate.of(2024, 9, 25),
        mati,
        heladera2,
        500,
        280.3F,
        false
    );

    vianda4 = new Vianda(comida4,
        LocalDate.of(2024, 10, 2),
        mati,
        heladera2,
        520,
        290.7F,
        false
    );

    vianda5 = new Vianda(comida5,
        LocalDate.of(2024, 10, 10),
        mati,
        heladera2,
        320,
        230.5F,
        false
    );

    vianda6 = new Vianda(comida6,
        LocalDate.of(2024, 10, 18),
        mati,
        heladera2,
        450,
        255.8F,
        false
    );

    vianda7 = new Vianda(comida7,
        LocalDate.of(2024, 10, 25),
        mati,
        heladera3,
        540,
        300.0F,
        false
    );

    vianda8 = new Vianda(comida8,
        LocalDate.of(2024, 11, 3),
        mati,
        heladera3,
        420,
        245.6F,
        false
    );

    vianda9 = new Vianda(comida9,
        LocalDate.of(2024, 11, 10),
        mati,
        heladera3,
        600,
        310.2F,
        false
    );


    lstViandas1.add(vianda1);
    lstViandas1.add(vianda2);

    lstViandas2.add(vianda3);
    lstViandas2.add(vianda4);
    lstViandas2.add(vianda5);
    lstViandas2.add(vianda6);

    lstViandas3.add(vianda7);
    lstViandas3.add(vianda8);
    lstViandas3.add(vianda9);

    heladera1.setViandas(lstViandas1);
    heladera2.setViandas(lstViandas2);
    heladera3.setViandas(lstViandas3);
  }

  static void iniciarDirecciones() {

    direccion1 = new Direccion();
    direccion1.setBarrio(barrio1);
    direccion1.setNombreUbicacion("Segurola 4310, Villa Devoto, CABA");
    direccion1.setLongitud(-58.517341F);
    direccion1.setLatitud(-34.605857F);

    direccion2 = new Direccion();
    direccion2.setBarrio(barrio2);
    direccion2.setNombreUbicacion("Ruy Díaz de Guzmán 675, Villa Alem, Rio Cuarto");
    direccion2.setLongitud(-64.353041F);
    direccion2.setLatitud(-33.136980F);

    direccion3 = new Direccion();
    direccion3.setBarrio(barrio3);
    direccion3.setNombreUbicacion("Lavalle 800, Villa Griselda, La Banda");
    direccion3.setLongitud(-64.239235F);
    direccion3.setLatitud(-27.732836F);
  }

  static void iniciarBarrios() {

    barrio1 = new Barrio();
    barrio1.setCiudad(ciudad1);
    barrio1.setNombreBarrio("Villa Devoto");
    barrio1.setCalle("Segurola");
    barrio1.setNumero(4310);

    barrio2 = new Barrio();
    barrio2.setCiudad(ciudad2);
    barrio2.setNombreBarrio("Villa Alem");
    barrio2.setNumero(675);
    barrio2.setCalle("Ruy Díaz de Guzmán");

    barrio3 = new Barrio();
    barrio3.setCiudad(ciudad3);
    barrio3.setNombreBarrio("Villa Griselda");
    barrio3.setNumero(800);
    barrio3.setCalle("Lavalle");
  }

  static void iniciarCiudades() {

    ciudad1 = new Ciudad();
    ciudad1.setProvincia(provincia1);
    ciudad1.setNombreCiudad("CABA");

    ciudad2 = new Ciudad();
    ciudad2.setProvincia(provincia2);
    ciudad2.setNombreCiudad("Rio cuarto");

    ciudad3 = new Ciudad();
    ciudad3.setProvincia(provincia3);
    ciudad3.setNombreCiudad("La Banda");
  }

  static void iniciarProvincias() {
    provincia1 = new Provincia();
    provincia1.setNombreProvincia("Buenos Aires");

    provincia2 = new Provincia();
    provincia2.setNombreProvincia("Cordoba");

    provincia3 = new Provincia();
    provincia3.setNombreProvincia("Santiago del Estero");
  }

  static void iniciarEstados() {

    estadoActual1 = new Estado();
    estadoActual1.setEstado(TipoEstado.ACTIVA);
    estadoActual1.setFechaInicial(LocalDate.of(2021, 10, 10));
    estadoActual1.setFechaFinal(LocalDate.of(2021, 10, 11));

    estadoActual2 = new Estado();
    estadoActual2.setEstado(TipoEstado.ACTIVA);
    estadoActual2.setFechaInicial(LocalDate.of(2021, 11, 12));
    estadoActual2.setFechaFinal(LocalDate.of(2021, 11, 13));

    estadoActual3 = new Estado();
    estadoActual3.setEstado(TipoEstado.ACTIVA);
    estadoActual3.setFechaInicial(LocalDate.of(2021, 12, 14));
    estadoActual3.setFechaFinal(LocalDate.of(2021, 12, 15));

    lstEstados1 = new ArrayList<>();
    lstEstados2 = new ArrayList<>();
    lstEstados3 = new ArrayList<>();

    estado1 = new Estado();
    estado1.setEstado(TipoEstado.ACTIVA);
    estado1.setFechaInicial(LocalDate.of(2021, 10, 10));
    estado1.setFechaFinal(LocalDate.of(2021, 10, 11));
    lstEstados1.add(estado1);

    estado2 = new Estado();
    estado2.setEstado(TipoEstado.INACTIVA_FUNCIONAL);
    estado2.setFechaInicial(LocalDate.of(2021, 11, 12));
    estado2.setFechaFinal(LocalDate.of(2021, 11, 13));
    lstEstados2.add(estado2);

    estado3 = new Estado();
    estado3.setEstado(TipoEstado.INACTIVA_TEMPERATURA);
    estado3.setFechaInicial(LocalDate.of(2021, 12, 14));
    estado3.setFechaFinal(LocalDate.of(2021, 12, 15));
    lstEstados3.add(estado3);

    estado4 = new Estado();
    estado4.setEstado(TipoEstado.ACTIVA);
    estado4.setFechaInicial(LocalDate.of(2022, 1, 16));
    estado4.setFechaFinal(LocalDate.of(2022, 1, 17));
    lstEstados1.add(estado4);

    estado5 = new Estado();
    estado5.setEstado(TipoEstado.INACTIVA_FRAUDE);
    estado5.setFechaInicial(LocalDate.of(2022, 2, 18));
    estado5.setFechaFinal(LocalDate.of(2022, 2, 19));
    lstEstados2.add(estado5);
  }

  static void iniciarModelos() {
    modelo1 = new Modelo();
    modelo1.setNombre("MK-2606");
    modelo1.setTemperaturaMaxima(18F);
    modelo1.setTemperaturaMinima(-15F);

    modelo2 = new Modelo();
    modelo2.setNombre("MK-2647");
    modelo2.setTemperaturaMaxima(20F);
    modelo2.setTemperaturaMinima(-10F);

    modelo3 = new Modelo();
    modelo3.setNombre("MK-2688");
    modelo3.setTemperaturaMaxima(22F);
    modelo3.setTemperaturaMinima(-5F);
  }

  static void iniciarOfertas() {
    lstOfertas = new ArrayList<>();

    oferta1 = new Oferta();
    oferta1.setNombre("Oferton");
    oferta1.setPuntosNecesarios(1000F);
    oferta1.setOfertante(elCityGroup);

    oferta2 = new Oferta();
    oferta2.setNombre("Estafa ponzi");
    oferta2.setPuntosNecesarios(2877.045F);
    oferta2.setOfertante(elCityGroup);

    oferta3 = new Oferta();
    oferta3.setNombre("Esta no le gusta al chavo fucks");
    oferta3.setPuntosNecesarios(45000.500F);
    oferta3.setOfertante(elCityGroup);

    lstOfertas.add(oferta1);
    lstOfertas.add(oferta2);
    lstOfertas.add(oferta3);
  }

  static void iniciarSensores() {
    sensorMovimiento1 = new SensorMovimiento();
    sensorMovimiento1.setHeladera(heladera1);

    sensorMovimiento2 = new SensorMovimiento();
    sensorMovimiento2.setHeladera(heladera2);

    sensorMovimiento3 = new SensorMovimiento();
    sensorMovimiento3.setHeladera(heladera3);

    sensorTemperatura1 = new SensorTemperatura();
    sensorTemperatura1.setHeladera(heladera1);

    sensorTemperatura2 = new SensorTemperatura();
    sensorTemperatura2.setHeladera(heladera2);

    sensorTemperatura3 = new SensorTemperatura();
    sensorTemperatura3.setHeladera(heladera3);
  }

  static void iniciarFormularios() {

    lstPreguntas1 = new ArrayList<>();

    opcion1 = new Opcion();
    opcion1.setOpcion("2");

    opcion2 = new Opcion();
    opcion2.setOpcion("3");

    opcion3 = new Opcion();
    opcion3.setOpcion("5");

    respuesta1 = new Respuesta();
    respuesta1.setPregunta(pregunta1);
    respuesta1.setRespuestaSingleChoice(opcion1);

    respuestaFormulario1 = new RespuestaFormulario();
    respuestaFormulario1.setFormulario(formulario1);
    respuestaFormulario1.setColaborador(mati);
    respuestaFormulario1.setDescripcion("tiene 2");
    respuestaFormulario1.setRespuestas(List.of(respuesta1));

    lstOpciones1 = new ArrayList<>();

    lstOpciones1.add(opcion1);
    lstOpciones1.add(opcion2);
    lstOpciones1.add(opcion3);

    pregunta1 = new Pregunta();
    pregunta1.setPregunta("Cuantos mundiales tiene uruguay?");
    pregunta1.setOpciones(lstOpciones1);
    pregunta1.setEsOpcional(true);
    pregunta1.setTipoDeSuRespuesta(TipoPregunta.MULTIPLE_CHOICE);

    respuesta2 = new Respuesta();
    respuesta2.setPregunta(pregunta2);
    respuesta2.setRespuestaSingleChoice(opcion6);

    respuestaFormulario2 = new RespuestaFormulario();
    respuestaFormulario2.setFormulario(formulario1);
    respuestaFormulario2.setColaborador(augusto);
    respuestaFormulario2.setDescripcion("tiene 700");
    respuestaFormulario2.setRespuestas(List.of(respuesta2));

    opcion4 = new Opcion();
    opcion4.setOpcion("500");

    opcion5 = new Opcion();
    opcion5.setOpcion("600");

    opcion6 = new Opcion();
    opcion6.setOpcion("700");

    lstOpciones2 = new ArrayList<>();

    lstOpciones2.add(opcion4);
    lstOpciones2.add(opcion5);
    lstOpciones2.add(opcion6);

    pregunta2 = new Pregunta();
    pregunta2.setPregunta("Cuantas lineas tiene este test?");
    pregunta2.setOpciones(lstOpciones2);
    pregunta2.setEsOpcional(false);
    pregunta2.setTipoDeSuRespuesta(TipoPregunta.MULTIPLE_CHOICE);

    respuesta3 = new Respuesta();
    respuesta3.setPregunta(pregunta3);
    respuesta3.setRespuestaSingleChoice(opcion7);

    respuestaFormulario3 = new RespuestaFormulario();
    respuestaFormulario3.setFormulario(formulario1);
    respuestaFormulario3.setColaborador(iniaki);

    opcion7 = new Opcion();
    opcion7.setOpcion("1");

    opcion8 = new Opcion();
    opcion8.setOpcion("2");

    opcion9 = new Opcion();
    opcion9.setOpcion("3");

    lstOpciones3 = new ArrayList<>();

    pregunta3 = new Pregunta();
    pregunta3.setPregunta("Cuantas intercontinentales gano Velez?");
    pregunta3.setOpciones(lstOpciones3);
    pregunta3.setEsOpcional(true);
    pregunta3.setTipoDeSuRespuesta(TipoPregunta.SINGLE_CHOICE);

    lstPreguntas1.add(pregunta1);
    lstPreguntas1.add(pregunta2);
    lstPreguntas1.add(pregunta3);

    formulario1 = new Formulario();
    formulario1.setNombre("form");
    formulario1.setPreguntas(lstPreguntas1);
  }

  static void iniciarTecnicos() {
    liam = new Tecnico();
    liam.setNombre("Liam");
    liam.setApellido("Wilk");
    liam.setAreaDeCobertura(ciudad3);
    liam.setCuil("1459890002");
    liam.setContacto(contactoLiam);

    santi = new Tecnico();
    santi.setNombre("Santiago");
    santi.setApellido("Mendez");
    santi.setAreaDeCobertura(ciudad1);
    santi.setCuil("1178991234");
    santi.setContacto(contactoSanti);
  }

  static void iniciarSuscripciones() {
    desperfecto = new Desperfecto();
    desperfecto.setHeladera(heladera1);
    desperfecto.setColaborador(iniaki);
    desperfecto.setSenderInterface(EmailSender.getInstance());

    faltanViandas = new FaltanViandas();
    faltanViandas.setHeladera(heladera2);
    faltanViandas.setColaborador(mati);
    faltanViandas.setViandasFaltantes(5);
    faltanViandas.setSenderInterface(EmailSender.getInstance());

    quedanViandas = new QuedanViandas();
    quedanViandas.setHeladera(heladera3);
    quedanViandas.setColaborador(augusto);
    quedanViandas.setViandasDisponibles(2);
    quedanViandas.setSenderInterface(EmailSender.getInstance());

  }

  static void iniciarViandasPorColaborador() {
    viandasPorColaborador1 = new ViandasPorColaborador(augusto, 11);

    viandasPorColaborador2 = new ViandasPorColaborador(iniaki, 22);

    viandasPorColaborador3 = new ViandasPorColaborador(mati, 33);
  }

  static void iniciarReportes() {
    reporteHeladera1 = new ReporteHeladera(heladera1);
    reporteHeladera1.setPath("/home/usuario/reporte1.pdf");
    reporteHeladera1.setFallas(0);
    reporteHeladera1.setViandasRetiradas(5);
    reporteHeladera1.setViandasColocadas(11);
    reporteHeladera1.agregarNuevaColaboracion(viandasPorColaborador1);

    reporteHeladera2 = new ReporteHeladera(heladera2);
    reporteHeladera2.setPath("/home/usuario/reporte2.pdf");
    reporteHeladera2.setFallas(1);
    reporteHeladera2.setViandasRetiradas(5);
    reporteHeladera2.setViandasColocadas(22);
    reporteHeladera2.agregarNuevaColaboracion(viandasPorColaborador2);

    reporteHeladera3 = new ReporteHeladera(heladera3);
    reporteHeladera3.setPath("/home/usuario/reporte3.pdf");
    reporteHeladera3.setFallas(2);
    reporteHeladera3.setViandasRetiradas(7);
    reporteHeladera3.setViandasColocadas(33);
    reporteHeladera3.agregarNuevaColaboracion(viandasPorColaborador3);
  }

  static void iniciarRepos() {

    colaboradoresRepository =
        RepositoryLocator.instanceOf(ColaboradoresRepository.class);

    colaboracionesRepository =
        RepositoryLocator.instanceOf(ColaboracionesRepository.class);

    usosTarjetasVulnerablesRepository =
        RepositoryLocator.instanceOf(UsosTarjetasVulnerablesRepository.class);

    tecnicosRepository =
        RepositoryLocator.instanceOf(TecnicosRepository.class);

    repoGenerico =
        RepositoryLocator.instanceOf(GenericRepository.class);

    reportesRepository =
        RepositoryLocator.instanceOf(ReportesRepository.class);
  }

}