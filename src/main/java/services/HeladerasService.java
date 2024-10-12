package services;

import dtos.HeladeraInputDto;
import java.time.LocalDate;
import java.util.Objects;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.repositories.imp.GenericRepository;

/**
 * Service para instanciar heladeras a partir de sus DTOs.
 */

public class HeladerasService {

  private final GenericRepository genericRepository;

  public HeladerasService(GenericRepository heladerasRepository) {
    this.genericRepository = heladerasRepository;
  }

  /**
   * Constructor de la clase.
   *
   * @param heladeraInputDto el DTO con los datos de la heladera.
   */

  public void crear(HeladeraInputDto heladeraInputDto, Direccion direccion) {
    Heladera heladera = new Heladera();
    heladera.setNombre(heladeraInputDto.getNombre());
    heladera.setFechaDeCreacion(LocalDate.parse(heladeraInputDto.getFechaCreacion()));

    Modelo modelo = new Modelo();
    modelo.setNombre(heladeraInputDto.getModelo());
    modelo.setTemperaturaMaxima(Float.parseFloat(heladeraInputDto.getTemperaturaMax()));
    modelo.setTemperaturaMinima(Float.parseFloat(heladeraInputDto.getTemperaturaMin()));

    heladera.setModelo(modelo);

    heladera
        .setCapacidadMaximaViandas(Integer.valueOf(heladeraInputDto.getCapacidadMaximaViandas()));

    heladera.setDireccion(direccion);

    genericRepository.guardar(heladera);
  }

  /** Metodo del service que permite la modificacion de heladeras a partir de un dto.
   *
   * @param heladera heladera a modificar.
   * @param heladeraInputDto dto con los datos moficados.
   */

  public void modificar(Heladera heladera,
                        HeladeraInputDto heladeraInputDto) {

    if (!Objects.equals(heladeraInputDto.getNombre(), "")) {
      heladera.setNombre(heladeraInputDto.getNombre());
    }

    if (!Objects.equals(heladeraInputDto.getModelo(), "")) {
      heladera.getModelo().setNombre(heladeraInputDto.getModelo());
    }

    if (!Objects.equals(heladeraInputDto.getTemperaturaMax(), "")) {
      heladera.getModelo()
          .setTemperaturaMaxima(Float.parseFloat(heladeraInputDto.getTemperaturaMax()));
    }

    if (!Objects.equals(heladeraInputDto.getTemperaturaMin(), "")) {
      heladera.getModelo()
          .setTemperaturaMinima(Float.parseFloat(heladeraInputDto.getTemperaturaMin()));
    }

    if (!Objects.equals(heladeraInputDto.getCapacidadMaximaViandas(), "")) {
      heladera
          .setCapacidadMaximaViandas(Integer.valueOf(heladeraInputDto.getCapacidadMaximaViandas()));
    }

    if (!Objects.equals(heladeraInputDto.getFechaCreacion(), "")) {
      heladera.setFechaDeCreacion(LocalDate.parse(heladeraInputDto.getFechaCreacion()));
    }

    this.genericRepository.modificar(heladera);
  }
}