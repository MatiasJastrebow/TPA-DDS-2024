package services;

import dtos.VulnerableInputDto;
import java.time.LocalDate;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;
import models.repositories.imp.VulnerablesRepository;

/**
 * Service para los vulnerables.
 */

public class VulnerablesService {

  private final GenericRepository genericRepository;
  private final TarjetasVulnerablesRepository tarjetasVulnerablesRepository;
  private final VulnerablesRepository vulnerablesRepository;

  public VulnerablesService(
      GenericRepository genericRepository,
      TarjetasVulnerablesRepository tarjetasVulnerablesRepository,
      VulnerablesRepository vulnerablesRepository
  ) {
    this.genericRepository = genericRepository;
    this.tarjetasVulnerablesRepository = tarjetasVulnerablesRepository;
    this.vulnerablesRepository = vulnerablesRepository;
  }

  /** Crea un vulnerable a partir de un input.
   *
   * @param vulnerableInputDto el input de un vulnerable.
   * @param domicilio domicilio del vulnerable.
   */

  public Vulnerable crear(
      VulnerableInputDto vulnerableInputDto,
      Direccion domicilio,
      Colaborador colaborador
  ) {
    Vulnerable vulnerable = new Vulnerable();
    vulnerable.setNombre(vulnerableInputDto.getNombre());
    vulnerable.setFechaNacimiento(LocalDate.parse(vulnerableInputDto.getFechaNacimiento()));

    TipoDocumento tipoDocumento = TipoDocumento.valueOf(vulnerableInputDto.getTipoDocumento());
    Integer nroDocumento = Integer.parseInt(vulnerableInputDto.getNumeroDocumento());
    vulnerable.setDocumento(new Documento(nroDocumento, tipoDocumento));

    vulnerable.setDomicilio(domicilio);
    this.vulnerablesRepository.guardar(vulnerable);

    Optional<TarjetaVulnerable> posibleTarjeta =
        this.tarjetasVulnerablesRepository.buscarPorUuid(vulnerableInputDto.getTarjeta());

    TarjetaVulnerable tarjetaVulnerable = posibleTarjeta.get();

    RegistroVulnerable registroVulnerable =
        new RegistroVulnerable(colaborador, vulnerable, LocalDate.now());
    this.genericRepository.guardar(registroVulnerable);

    tarjetaVulnerable.setRegistroVulnerable(registroVulnerable);
    tarjetaVulnerable.calcularUsos();

    this.tarjetasVulnerablesRepository.modificar(tarjetaVulnerable);

    return vulnerable;
  }

  public void crearMenor(
      VulnerableInputDto menorInputDto,
      Vulnerable padre,
      Colaborador colaborador
  ) {

    Integer numeroDocumento = Integer.parseInt(menorInputDto.getNumeroDocumento());
    LocalDate fechaNacimiento = LocalDate.parse(menorInputDto.getFechaNacimiento());

    Optional<Vulnerable> posibleMenor =
        this.vulnerablesRepository.buscarPorDocumentoYFechaNacimiento(numeroDocumento, fechaNacimiento);

    Vulnerable menor;

    if (posibleMenor.isPresent()) {
      menor = posibleMenor.get();
    } else {
      menor = new Vulnerable();
      menor.setNombre(menorInputDto.getNombre());
      menor.setFechaNacimiento(fechaNacimiento);

      TipoDocumento tipoDocumento = TipoDocumento.valueOf(menorInputDto.getTipoDocumento());
      menor.setDocumento(new Documento(numeroDocumento, tipoDocumento));

      menor.setDomicilio(padre.getDomicilio());
      this.genericRepository.guardar(menor);

      RegistroVulnerable registroVulnerable =
          new RegistroVulnerable(colaborador, menor, LocalDate.now());
      this.genericRepository.guardar(registroVulnerable);
    }

    padre.agregarMenorCargo(menor);
    this.genericRepository.modificar(padre);
  }
}
