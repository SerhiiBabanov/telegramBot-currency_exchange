package command.editCommand;

import com.vdurmont.emoji.EmojiParser;
import model.ChatSetting;
import model.Valute;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class CAN extends EditCommand{
    public CAN() {
        commandName = "/setCAN";
        buttonText = "CAN";
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatSetting.getChatId());
        editMessageText.setMessageId(messageId);
        InlineKeyboardButton usd = new USD().getButton();
        InlineKeyboardButton can = new CAN().getButton();
        InlineKeyboardButton pzl = new PZL().getButton();
        if (chatSetting.getValutes().contains(Valute.USD)){
            usd.setText(EmojiParser.parseToUnicode(":white_check_mark:" + usd.getText()));
        }
        if (chatSetting.getValutes().contains(Valute.CAN)){
            List<Valute> valutes = chatSetting.getValutes();
            valutes.remove(Valute.CAN);
            chatSetting.setValutes(valutes);
            repository.add(chatSetting.getChatId(), chatSetting);
        } else {
            List<Valute> valutes = chatSetting.getValutes();
            valutes.add(Valute.CAN);
            chatSetting.setValutes(valutes);
            repository.add(chatSetting.getChatId(), chatSetting);
            can.setText(EmojiParser.parseToUnicode(":white_check_mark:" + can.getText()));
        }
        if (chatSetting.getValutes().contains(Valute.PLZ)){
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
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        editMessageText.setText("Change setting");
        return editMessageText;
    }
}
