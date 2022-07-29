package command.setting.roundResults.options;

import com.vdurmont.emoji.EmojiParser;
import model.EditCommand;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class RoundToTwo extends EditCommand {
    public RoundToTwo() {
        commandName = "/roundDigitSettingTwo";
        buttonText = "2";
        commandResultText = "Your round - 2";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(2);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
