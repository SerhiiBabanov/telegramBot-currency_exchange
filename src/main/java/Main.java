import command.EditCommand;
import command.SendCommand;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        List<SendCommand> sendCommands = new ArrayList<>();
        List<EditCommand> editCommands = new ArrayList<>();
        // Register our bot
        try {
            telegramBotsApi.registerBot(new TelegramBot(editCommands, sendCommands));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}