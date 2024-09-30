package services;

import config.ServiceLocator;
import dtos.DireccionInputDto;
import dtos.VulnerableInputDto;
import java.time.LocalDate;
import models.entities.direccion.Barrio;
import models.entities.direccion.Direccion;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.vulnerable.Vulnerable;

/**
 * Service para los vulnerables.
 */

//TODO REVISAR
public class VulnerablesService {

  /**
   * Crea un vulnerable a partir de un DTO.
   *
   * @param vulnerableInputDto el DTO con los datos del vulnerable.
   * @return el vulnerable creado.
   */

  public Vulnerable crear(VulnerableInputDto vulnerableInputDto) {
    Vulnerable vulnerable = new Vulnerable();
    vulnerable.setNombre(vulnerableInputDto.getNombre());
    vulnerable.setFechaNacimiento(LocalDate.parse(vulnerableInputDto.getFechaNacimiento()));

    TipoDocumento tipoDocumento = TipoDocumento.valueOf(vulnerableInputDto.getTipoDocumento());
    Integer nroDocumento = Integer.parseInt(vulnerableInputDto.getNumeroDocumento());
    vulnerable.setDocumento(new Documento(nroDocumento, tipoDocumento));

    DireccionInputDto direccionInputDto = DireccionInputDto.builder()
            .provincia(vulnerableInputDto.getProvincia())
            .nombreCiudad(vulnerableInputDto.getCuidad())
            .nombreBarrio(vulnerableInputDto.getBarrio())
            .calle(vulnerableInputDto.getCalle())
            .numero(vulnerableInputDto.getNumero())
            .build();

    vulnerable.setDomicilio(ServiceLocator.instanceOf(DireccionesService.class)
            .crear(direccionInputDto));

    return vulnerable;
  }
}