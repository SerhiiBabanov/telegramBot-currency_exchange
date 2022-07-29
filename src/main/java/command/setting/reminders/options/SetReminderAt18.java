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

public class SetReminderAt18 extends EditCommand {
    public SetReminderAt18() {
        commandName = "/SetReminderAt18";
        buttonText = "18";
        commandResultText = "Your time - 18";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(18);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
