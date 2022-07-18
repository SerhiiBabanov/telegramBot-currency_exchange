package command.sendCommand;

import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Setting extends SendCommand {
    public Setting() {
        commandName = "/setting";
        buttonText = "Setting";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatSetting.getChatId());
        sendMessage.setText(buttonText);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        rowInLine.add(new RoundDigitSetting().getButton());
        rowsInLine.add(rowInLine);
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        rowInLine1.add(new BankSetting().getButton());
        rowsInLine.add(rowInLine1);
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        rowInLine2.add(new ValuteSetting().getButton());
        rowsInLine.add(rowInLine2);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
