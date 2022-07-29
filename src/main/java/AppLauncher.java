import command.setting.reminders.ReminderSetting;
import command.setting.reminders.options.*;
import model.ChatSetting;
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
    private static List<SendCommand> sendCommands;

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        Repository chatSettings = new InMemoryListRepository();

        sendCommands = new ArrayList<>();
        addSendCommands(sendCommands);

        List<EditCommand> editCommands = new ArrayList<>();
        addEditCommands(editCommands);

        try {
            telegramBotsApi.registerBot(new TelegramBot(editCommands, sendCommands, chatSettings));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private static void addEditCommands(List<EditCommand> editCommands) {
        editCommands.add(new RoundToTwo(sendCommands.get(2)));
        editCommands.add(new RoundToTree(sendCommands.get(2)));
        editCommands.add(new RoundToFour(sendCommands.get(2)));
        editCommands.add(new SetBankMonobank(sendCommands.get(4)));
        editCommands.add(new SetBankNBU(sendCommands.get(4)));
        editCommands.add(new SetBankPrivatbank(sendCommands.get(4)));
        editCommands.add(new PLZ(sendCommands.get(5)));
        editCommands.add(new USD(sendCommands.get(5)));
        editCommands.add(new CAD(sendCommands.get(5)));
        editCommands.add(new SetReminderAt9(sendCommands.get(6)));
        editCommands.add(new SetReminderAt10(sendCommands.get(6)));
        editCommands.add(new SetReminderAt11(sendCommands.get(6)));
        editCommands.add(new SetReminderAt12(sendCommands.get(6)));
        editCommands.add(new SetReminderAt13(sendCommands.get(6)));
        editCommands.add(new SetReminderAt14(sendCommands.get(6)));
        editCommands.add(new SetReminderAt15(sendCommands.get(6)));
        editCommands.add(new SetReminderAt16(sendCommands.get(6)));
        editCommands.add(new SetReminderAt17(sendCommands.get(6)));
        editCommands.add(new SetReminderAt18(sendCommands.get(6)));
        editCommands.add(new SetReminderAtNone(sendCommands.get(6)));
    }

    private static void addSendCommands(List<SendCommand> sendCommands) {
        sendCommands.add(new Start());              //0
        sendCommands.add(new Setting());            //1
        sendCommands.add(new RoundSetting());       //2
        sendCommands.add(new GetInfo());            //3
        sendCommands.add(new BankSetting());        //4
        sendCommands.add(new CurrencySetting());    //5
        sendCommands.add(new ReminderSetting());    //6
    }

}