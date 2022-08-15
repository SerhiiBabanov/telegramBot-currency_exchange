package model;

import banksUtils.monobank.MonobankUtils;
import banksUtils.nbu.NBUUtils;
import banksUtils.privatbank.PrivatbankUtils;
import command.info.GetInfo;
import command.setting.Setting;
import command.setting.bank.BankSetting;
import command.setting.bank.options.SetBankMonobank;
import command.setting.bank.options.SetBankNBU;
import command.setting.bank.options.SetBankPrivatbank;
import command.setting.currency.CurrencySetting;
import command.setting.currency.options.CAD;
import command.setting.currency.options.PLZ;
import command.setting.currency.options.USD;
import command.setting.languages.LanguagesSetting;
import command.setting.languages.options.LanguageUA;
import command.setting.languages.options.LanguageUS;
import command.setting.reminders.ReminderSetting;
import command.setting.reminders.options.*;
import command.setting.roundResults.RoundSetting;
import command.setting.roundResults.options.RoundToFour;
import command.setting.roundResults.options.RoundToTree;
import command.setting.roundResults.options.RoundToTwo;
import command.start.Start;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repository.Repository;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TelegramBot extends TelegramLongPollingBot {
    private static String homeButtonText = "Додому";
    private static String backButtonText = "Назад";
    private static final List<EditCommand> editCommands = new ArrayList<>();
    private static final List<SendCommand> sendCommands = new ArrayList<>();
    private static final List<Bank> banks = new ArrayList<>();
    private static final int TIME_ZONE = 3;


    public final Repository repository;

    public TelegramBot(Repository repository) {
        super();
        setEditCommandList();
        setSendCommandList();
        setBanks();
        this.repository = repository;
        startScheduledTasks();
        startUpdateBankInfoTask();
    }

    public static List<EditCommand> getEditCommands() {
        return List.copyOf(editCommands);
    }

    public static List<SendCommand> getSendCommands() {
        return List.copyOf(sendCommands);
    }

    public static List<Bank> getBanks() {
        return List.copyOf(banks);
    }

    public static void changeLocal(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.Resource", locale);

        setHomeButtonText(bundle.getString("homeButtonText"));
        setBackButtonText(bundle.getString("backButtonText"));

        GetInfo.setBuyText(bundle.getString("buyText"));
        GetInfo.setCourseText(bundle.getString("courseText"));
        GetInfo.setResultText(bundle.getString("resultText"));
        GetInfo.setSellText(bundle.getString("sellText"));

        banks.stream().forEach(bank -> bank.setName(bundle.getString(bank.getCommandName())));

        Stream.concat(editCommands.stream(), sendCommands.stream())
                .forEach(command -> {
                    command.setCommandResultText(bundle.getString(command.getCommandName() + "commandResultText".toUpperCase()));
                    command.setButtonText(bundle.getString(command.getCommandName() + "buttonText".toUpperCase()));
                });

        sendCommands.stream().forEach(SendCommand::setSettingsButtons);



    }

    private static void setBanks() {
        banks.add(new PrivatbankUtils());
        banks.add(new MonobankUtils());
        banks.add(new NBUUtils());
    }

    private static void setEditCommandList() {
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
        editCommands.add(new LanguageUA());
        editCommands.add(new LanguageUS());
    }

    private static void setSendCommandList() {
        sendCommands.add(new RoundSetting());       //2
        sendCommands.add(new GetInfo());            //3
        sendCommands.add(new BankSetting());        //4
        sendCommands.add(new CurrencySetting());    //5
        sendCommands.add(new ReminderSetting());    //6
        sendCommands.add(new LanguagesSetting());
        sendCommands.add(new Setting());
        sendCommands.add(new Start());
    }

    private void startScheduledTasks() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int pauseBeforeStartFirstTask = (60 - LocalTime.now().getMinute()) * 60;

        Runnable task1 = () -> {
            int hour = LocalTime.now().getHour() + TIME_ZONE;
            List<ChatSetting> settings = this.repository.getListOfSettings();
            for (ChatSetting ch : settings
            ) {

                if (ch.getReminderTime() == hour) {
                    Update update = new Update();
                    Message message = new Message();
                    Chat chat = new Chat();
                    chat.setId(ch.getChatId());
                    message.setChat(chat);
                    message.setText("/getInfo");
                    update.setMessage(message);
                    onUpdateReceived(update);
                }


            }
        };
        executor.scheduleAtFixedRate(task1, pauseBeforeStartFirstTask, 3600, TimeUnit.SECONDS);
    }

    private void startUpdateBankInfoTask() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int pauseBeforeStartFirstTask = 60;

        Runnable task1 = () -> {
            MonobankUtils.updateExchangeList();
            PrivatbankUtils.updateExchangeList();
            NBUUtils.updateExchangeList();
        };
        executor.scheduleAtFixedRate(task1, pauseBeforeStartFirstTask, 60, TimeUnit.SECONDS);
    }

    @Override
    public String getBotUsername() {
        return "goit_java_telegram_bot";
       // return "testBotjava1221bot";
    }

    @Override
    public String getBotToken() {
        return "5588477547:AAHAGkA7oAgtwJTdH34DIC5DocwS_dSKOvY";
       // return "5728357202:AAGZMzZVJCP-wXxm4AQrRO8_vx8Nge8lVu4";
    }

    public static String getHomeButtonText() {
        return homeButtonText;
    }

    public static String getBackButtonText() {
        return backButtonText;
    }

    public static void setHomeButtonText(String homeButtonText) {
        TelegramBot.homeButtonText = homeButtonText;
    }

    public static void setBackButtonText(String backButtonText) {
        TelegramBot.backButtonText = backButtonText;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            ChatSetting chatSetting = repository.contains(chatId) ? repository.getSetting(chatId) : ChatSetting.getDefault(chatId);
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
            ChatSetting chatSetting = repository.contains(chatId) ? repository.getSetting(chatId) : ChatSetting.getDefault(chatId);
            EditMessageText editMessage = null;
            for (EditCommand command : editCommands) {
                if (command.canExecute(callData)) {
                    editMessage = command.execute(chatSetting, messageId, repository);
                    break;
                }
            }
            SendMessage message = null;
            if (Objects.isNull(editMessage)) {
                for (SendCommand command : sendCommands) {
                    if (command.canExecute(callData)) {
                        message = command.execute(chatSetting);
                        break;
                    }
                }
            }
            try {
                if (Objects.nonNull(editMessage)) {
                    execute(editMessage);
                }
                if (Objects.nonNull(message)) {
                    execute(message);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
}
