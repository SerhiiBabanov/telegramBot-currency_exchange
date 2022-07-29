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

public class CAD extends EditCommand {
    public CAD() {
        commandName = "/setCAD";
        buttonText = "CAD";
        commandResultText = "Зміни збережено";
    }


    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        List<Currency> currencies = chatSetting.getValutes();
        if (chatSetting.getValutes().contains(Currency.CAD)){
            currencies.remove(Currency.CAD);
        } else {
            currencies.add(Currency.CAD);
        }
        chatSetting.setValutes(currencies);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
