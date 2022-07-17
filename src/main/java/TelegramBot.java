import command.EditCommand;
import command.SendCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    private List<EditCommand> editCommands;
    private List<SendCommand> sendCommands;

    public TelegramBot(List<EditCommand> editCommands, List<SendCommand> sendCommands) {
        super();
        this.editCommands = editCommands;
        this.sendCommands = sendCommands;
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            for (SendCommand command : sendCommands) {
                if (command.canExecute(messageText)) {
                    message = command.execute(chatId);
                }
            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            EditMessageText editMessage = new EditMessageText();
            for (EditCommand command : editCommands) {
                if (command.canExecute(callData)) {
                    editMessage  = command.execute(chatId);
                }
            }
            try {
                execute(editMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
}
