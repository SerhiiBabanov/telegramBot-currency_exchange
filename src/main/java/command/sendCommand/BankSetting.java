package command.sendCommand;

import com.vdurmont.emoji.EmojiParser;
import command.editCommand.*;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class BankSetting extends SendCommand{
    public BankSetting() {
        commandName = "/bankSetting";
        buttonText = "BankSetting";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatSetting.getChatId());
        sendMessage.setText(commandName);
        InlineKeyboardButton monobank = new SetBankMonobank().getButton();
        InlineKeyboardButton privatbank = new SetBankPrivatbank().getButton();
        InlineKeyboardButton nbu = new SetBankNBU().getButton();

        if (chatSetting.getBank().toString().equals("Monobank")){
            monobank.setText(EmojiParser.parseToUnicode(":white_check_mark:" + monobank.getText()));
        }
        if (chatSetting.getBank().toString().equals("NBU")){
            nbu.setText(EmojiParser.parseToUnicode(":white_check_mark:" + nbu.getText()));
        }
        if (chatSetting.getBank().toString().equals("Privatbank")){
            privatbank.setText(EmojiParser.parseToUnicode(":white_check_mark:" + privatbank.getText()));
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine0 = new ArrayList<>();
        rowInLine0.add(monobank);
        rowsInLine.add(rowInLine0);
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        rowInLine1.add(privatbank);
        rowsInLine.add(rowInLine1);
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        rowInLine2.add(nbu);
        rowsInLine.add(rowInLine2);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
