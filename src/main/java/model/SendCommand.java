package model;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public abstract class SendCommand extends Command {
    public SendCommand(String commandName, String buttonText, String commandResultText) {
        super(commandName, buttonText, commandResultText);
    }

    public SendMessage execute(ChatSetting chatSetting){
        return SendMessage.builder()
                .text(buttonText)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(getKeyboard(chatSetting)).build())
                .chatId(chatSetting.getChatId())
                .build();
    }
    public abstract List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting);
}
