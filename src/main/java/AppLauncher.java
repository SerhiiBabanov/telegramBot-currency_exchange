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
import model.TelegramBot;
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

        Repository chatSettings = new InMemoryListRepository();

        try {
            telegramBotsApi.registerBot(new TelegramBot(chatSettings));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }





}