package command.setting.Currency.options;

import com.vdurmont.emoji.EmojiParser;
import model.EditCommand;
import model.ChatSetting;
import model.Currency;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class USD extends EditCommand {
    public USD() {
        commandName = "/setUSD";
        buttonText = "USD";
    }

    @Override

    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatSetting.getChatId());
        editMessageText.setMessageId(messageId);
        InlineKeyboardButton usd = new USD().getButton();
        InlineKeyboardButton can = new CAD().getButton();
        InlineKeyboardButton pzl = new PLZ().getButton();
        if (chatSetting.getValutes().contains(Currency.CAD)){
            can.setText(EmojiParser.parseToUnicode(":white_check_mark:" + can.getText()));
        }
        if (chatSetting.getValutes().contains(Currency.USD)){
            List<Currency> currencies = chatSetting.getValutes();
            currencies.remove(Currency.USD);
            chatSetting.setValutes(currencies);
            repository.add(chatSetting.getChatId(), chatSetting);
        } else {
            List<Currency> currencies = chatSetting.getValutes();
            currencies.add(Currency.USD);
            chatSetting.setValutes(currencies);
            repository.add(chatSetting.getChatId(), chatSetting);
            usd.setText(EmojiParser.parseToUnicode(":white_check_mark:" + usd.getText()));
        }
        if (chatSetting.getValutes().contains(Currency.PLZ)){
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
