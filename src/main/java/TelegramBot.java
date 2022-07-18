import command.editCommand.EditCommand;
import command.sendCommand.SendCommand;
import model.ChatSetting;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repository.Repository;

import java.util.List;
import java.util.Objects;

public class TelegramBot extends TelegramLongPollingBot {
    private final List<EditCommand> editCommands;
    private final List<SendCommand> sendCommands;

    public final Repository chatSettings;

    public TelegramBot(List<EditCommand> editCommands, List<SendCommand> sendCommands, Repository chatSettings) {
        super();
        this.editCommands = editCommands;
        this.sendCommands = sendCommands;
        this.chatSettings = chatSettings;
    }

    @Override
    public String getBotUsername() {
        return "goit_java_telegram_bot";
    }

    @Override
    public String getBotToken() {
        return "5588477547:AAHAGkA7oAgtwJTdH34DIC5DocwS_dSKOvY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            ChatSetting chatSetting = chatSettings.contains(chatId) ? chatSettings.getSetting(chatId) : ChatSetting.getDefault(chatId);
            for (SendCommand command : sendCommands) {
                if (command.canExecute(messageText)) {
                    message = command.execute(chatSetting);
                }
            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            ChatSetting chatSetting = chatSettings.contains(chatId) ? chatSettings.getSetting(chatId) : ChatSetting.getDefault(chatId);
            EditMessageText editMessage = null;
            for (EditCommand command : editCommands) {
                if (command.canExecute(callData)) {
                    editMessage  = command.execute(chatSetting, messageId, chatSettings);
                }
            }
            SendMessage message = null;
            for (SendCommand command : sendCommands) {
                if (command.canExecute(callData)) {
                    message = command.execute(chatSetting);
                }
            }
            try {
                if(Objects.nonNull(editMessage)){
                    execute(editMessage);
                }
                if (Objects.nonNull(message)){
                    execute(message);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
}
