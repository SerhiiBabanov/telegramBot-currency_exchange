package command.setting.reminders.options;

import com.vdurmont.emoji.EmojiParser;
import command.setting.roundResults.options.RoundToFour;
import command.setting.roundResults.options.RoundToTree;
import command.setting.roundResults.options.RoundToTwo;
import model.ChatSetting;
import model.EditCommand;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class SetReminderAt9 extends EditCommand {
    public SetReminderAt9() {
        commandName = "/SetReminderAt9";
        buttonText = "9";
        commandResultText = "Your time - 9";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(9);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
