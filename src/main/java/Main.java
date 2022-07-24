import command.editCommand.*;
import command.sendCommand.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import repository.InMemoryMapRepository;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        Repository settingsChat = new InMemoryMapRepository();

        List<SendCommand> sendCommands = new ArrayList<>();
        sendCommands.add(new Start());
        sendCommands.add(new Setting());
        sendCommands.add(new RoundDigitSetting());
        sendCommands.add(new GetInfo());
        sendCommands.add(new BankSetting());
        sendCommands.add(new ValuteSetting());

        List<EditCommand> editCommands = new ArrayList<>();
        editCommands.add(new RoundDigitSettingTwo());
        editCommands.add(new RoundDigitSettingTree());
        editCommands.add(new RoundDigitSettingFour());
        editCommands.add(new SetBankMonobank());
        editCommands.add(new SetBankNBU());
        editCommands.add(new SetBankPrivatbank());
        editCommands.add(new PLZ());
        editCommands.add(new USD());
        editCommands.add(new CAD());
        // Register our bot

        try {
            telegramBotsApi.registerBot(new TelegramBot(editCommands, sendCommands, settingsChat));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}