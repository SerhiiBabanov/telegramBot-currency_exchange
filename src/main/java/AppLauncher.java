import command.setting.reminders.ReminderSetting;
import command.setting.reminders.options.*;
import model.EditCommand;
import model.SendCommand;
import command.info.GetInfo;
import command.setting.Currency.CurrencySetting;
import command.setting.Currency.options.CAD;
import command.setting.Currency.options.PLZ;
import command.setting.Currency.options.USD;
import command.setting.Setting;
import command.setting.bank.BankSetting;
import command.setting.bank.options.SetBankMonobank;
import command.setting.bank.options.SetBankNBU;
import command.setting.bank.options.SetBankPrivatbank;
import command.setting.roundResults.RoundSetting;
import command.setting.roundResults.options.RoundToFour;
import command.setting.roundResults.options.RoundToTree;
import command.setting.roundResults.options.RoundToTwo;
import command.start.Start;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import repository.InMemoryListRepository;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class AppLauncher {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        Repository settingsChat = new InMemoryListRepository();

        List<SendCommand> sendCommands = new ArrayList<>();
        addSendCommands(sendCommands);

        List<EditCommand> editCommands = new ArrayList<>();
        addEditCommands(editCommands);
        // Register our bot

        try {
            telegramBotsApi.registerBot(new TelegramBot(editCommands, sendCommands, settingsChat));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private static void addEditCommands(List<EditCommand> editCommands) {
        editCommands.add(new RoundToTwo());
        editCommands.add(new RoundToTree());
        editCommands.add(new RoundToFour());
        editCommands.add(new SetBankMonobank());
        editCommands.add(new SetBankNBU());
        editCommands.add(new SetBankPrivatbank());
        editCommands.add(new PLZ());
        editCommands.add(new USD());
        editCommands.add(new CAD());
        editCommands.add(new SetReminderAt9());
        editCommands.add(new SetReminderAt10());
        editCommands.add(new SetReminderAt11());
        editCommands.add(new SetReminderAt12());
        editCommands.add(new SetReminderAt13());
        editCommands.add(new SetReminderAt14());
        editCommands.add(new SetReminderAt15());
        editCommands.add(new SetReminderAt16());
        editCommands.add(new SetReminderAt17());
        editCommands.add(new SetReminderAt18());
        editCommands.add(new SetReminderAtNone());
    }

    private static void addSendCommands(List<SendCommand> sendCommands) {
        sendCommands.add(new Start());
        sendCommands.add(new Setting());
        sendCommands.add(new RoundSetting());
        sendCommands.add(new GetInfo());
        sendCommands.add(new BankSetting());
        sendCommands.add(new CurrencySetting());
        sendCommands.add(new ReminderSetting());
    }
}