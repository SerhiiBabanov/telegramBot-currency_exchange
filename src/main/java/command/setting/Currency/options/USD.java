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
        commandResultText = "Зміни збережено";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        List<Currency> currencies = chatSetting.getValutes();
        if (chatSetting.getValutes().contains(Currency.USD)){
            currencies.remove(Currency.USD);
        } else {
            currencies.add(Currency.USD);
        }
        chatSetting.setValutes(currencies);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
