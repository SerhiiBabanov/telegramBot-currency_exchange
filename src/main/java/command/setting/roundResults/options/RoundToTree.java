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

public class RoundToTree extends EditCommand {
    public RoundToTree() {
        commandName = "/roundDigitSettingTree";
        buttonText = "3";
        commandResultText = "Your round - 3";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(3);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
