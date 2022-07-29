package command.setting.reminders.options;

import com.vdurmont.emoji.EmojiParser;
import command.setting.roundResults.options.RoundToFour;
import model.ChatSetting;
import model.EditCommand;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class SetReminderAt17 extends EditCommand {
    public SetReminderAt17() {
        commandName = "/SetReminderAt17";
        buttonText = "17";
        commandResultText = "Your time - 17";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(17);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
