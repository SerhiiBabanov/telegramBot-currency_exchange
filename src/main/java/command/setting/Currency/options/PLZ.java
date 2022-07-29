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

public class PLZ extends EditCommand {
    public PLZ() {
        commandName = "/setPLZ";
        buttonText = "PLZ";
        commandResultText = "Зміни збережено";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        List<Currency> currencies = chatSetting.getValutes();
        if (chatSetting.getValutes().contains(Currency.PLZ)){
            currencies.remove(Currency.PLZ);
        } else {
            currencies.add(Currency.PLZ);
        }
        chatSetting.setValutes(currencies);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
