package csv;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import controllers.CsvController;
import models.repositories.personas.InterfaceColaboradoresRepository;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ColaboradoresService;
import utils.sender.Destinatario;
import utils.sender.Mensaje;
import utils.sender.channels.EmailSender;

public class TestCSV {
  CsvController csvController;
  ColaboradoresService colaboradoresService;
  InterfaceColaboradoresRepository colaboradoresRepository;
  ColaboracionesRepository colaboracionesRepository;

  @BeforeEach
  void inicializar() {
    colaboradoresRepository = new ColaboradoresRepository();
    colaboracionesRepository = new ColaboracionesRepository();
    EmailSender emailSender = mock(EmailSender.class);
    doNothing().when(emailSender).enviar(any(Mensaje.class), any(Destinatario.class));
    colaboradoresService = new ColaboradoresService(colaboradoresRepository, emailSender);
    csvController = new CsvController(colaboradoresRepository, colaboracionesRepository, colaboradoresService);
  }

  @Test
  @DisplayName("Se realiza la carga, se guardan los usuarios y se les envia un mail")
  public void losUsuariosRecibenMail(){
    csvController.leerArchivoCsv();
    Assertions.assertTrue(colaboradoresRepository.buscar(12345678).isPresent());
  }

}