package command.sendCommand;

import com.vdurmont.emoji.EmojiParser;
import command.editCommand.*;
import model.ChatSetting;
import model.Valute;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ValuteSetting extends SendCommand{
    public ValuteSetting() {
        commandName = "/valuteSetting";
        buttonText = "Valute setting";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatSetting.getChatId());
        sendMessage.setText(commandName);
        InlineKeyboardButton usd = new USD().getButton();
        InlineKeyboardButton can = new CAN().getButton();
        InlineKeyboardButton pzl = new PZL().getButton();
        if (chatSetting.getValutes().contains(Valute.USD)){
            usd.setText(EmojiParser.parseToUnicode(":white_check_mark:" + usd.getText()));
        }
        if (chatSetting.getValutes().contains(Valute.CAN)){
            can.setText(EmojiParser.parseToUnicode(":white_check_mark:" + can.getText()));
        }
        if (chatSetting.getValutes().contains(Valute.PZL)){
            pzl.setText(EmojiParser.parseToUnicode(":white_check_mark:" + pzl.getText()));
        }
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine0 = new ArrayList<>();
        rowInLine0.add(usd);
        rowsInLine.add(rowInLine0);
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        rowInLine1.add(can);
        rowsInLine.add(rowInLine1);
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        rowInLine2.add(pzl);
        rowsInLine.add(rowInLine2);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
