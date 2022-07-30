package model;

import banksUtils.monobank.MonobankUtils;
import banksUtils.nbu.NBUUtils;
import banksUtils.privatbank.PrivatbankUtils;
import model.EditCommand;
import model.SendCommand;
import model.ChatSetting;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repository.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TelegramBot extends TelegramLongPollingBot {
    private static  List<EditCommand> editCommands = null;
    private static  List<SendCommand> sendCommands = null;

    public final Repository chatSettings;

    public TelegramBot(List<EditCommand> editCommands, List<SendCommand> sendCommands, Repository chatSettings) {
        super();
        TelegramBot.editCommands = editCommands;
        TelegramBot.sendCommands = sendCommands;
        this.chatSettings = chatSettings;
        startScheduledTasks();
        startUpdateBankInfoTask();


    }

    private void startScheduledTasks() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int pauseBeforeStartFirstTask = (60 - LocalTime.now().getMinute()) * 60;

        Runnable task1 = () -> {
            int hour = LocalTime.now().getHour();
            List<ChatSetting> settings = this.chatSettings.getListOfSettings();
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

    public static List<EditCommand> getEditCommands() {
        return editCommands;
    }

    public static List<SendCommand> getSendCommands() {
        return sendCommands;
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
                    editMessage = command.execute(chatSetting, messageId, chatSettings);
                }
            }
            SendMessage message = null;
            for (SendCommand command : sendCommands) {
                if (command.canExecute(callData)) {
                    message = command.execute(chatSetting);
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
