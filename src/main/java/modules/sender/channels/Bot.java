package modules.sender.channels;

import java.util.List;
import modules.sender.Config;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/*
  falta lo mas importante, crear al bot
 */

public class Bot extends TelegramLongPollingBot {

  @Override
  public String getBotUsername() {
    return Config.getTelegramBotUser();
  }

  @Override
  public String getBotToken() {
    return Config.getTelegramBotToken();
  }

  @Override
  public void onUpdateReceived(Update update) {

    if (update.hasMessage() && update.getMessage().hasText()) {
      String messageText = update.getMessage().getText();
      Long chatId = update.getMessage().getChatId();

      if (messageText.equalsIgnoreCase("/start")) {
        sendText(chatId, "¡Bienvenido! ¿En qué puedo ayudarte?");
      } else if (messageText.equalsIgnoreCase("/help")) {
        sendText(chatId, "Estos son los comandos disponibles:\n/start - Iniciar\n/help - Ayuda");
      } else {
        sendText(chatId, "Mensaje recibido: " + messageText);
      }
    }
  }


  @Override
  public void onUpdatesReceived(List<Update> updates) {
    super.onUpdatesReceived(updates);
  }

  public void sendText(Long who, String what){
    SendMessage sm = SendMessage.builder()
        .chatId(who.toString())
        .text(what).build();
    try {
      execute(sm);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }
}