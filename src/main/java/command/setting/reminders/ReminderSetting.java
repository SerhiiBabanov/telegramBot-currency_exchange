package command.setting.reminders;

import com.vdurmont.emoji.EmojiParser;
import command.setting.reminders.options.*;
import model.ChatSetting;
import model.SendCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ReminderSetting extends SendCommand {
    public ReminderSetting() {
        commandName = "/reminderSetting";
        buttonText = "Час сповіщень";
    }


    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> reminderButtons = new ArrayList<>();
        reminderButtons.add(List.of(
                new SetReminderAt9().getButton(),
                new SetReminderAt10().getButton(),
                new SetReminderAt11().getButton()));
        reminderButtons.add(List.of(
                new SetReminderAt12().getButton(),
                new SetReminderAt13().getButton(),
                new SetReminderAt14().getButton()));
        reminderButtons.add(List.of(
                new SetReminderAt15().getButton(),
                new SetReminderAt16().getButton(),

                new SetReminderAt17().getButton()));
        reminderButtons.add(List.of(
                new SetReminderAt18().getButton(),
                new SetReminderAtNone().getButton()
        ));
        for (List<InlineKeyboardButton> row : reminderButtons
        ) {
            for (InlineKeyboardButton button : row
            ) {
                if (button.getText().equals(String.valueOf(chatSetting.getReminderTime()))) {
                    button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                }
                if (chatSetting.getReminderTime() == 25 & button.getText().equals("Turn off reminder")) {
                    button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                }

            }

        }
        return reminderButtons;
    }
}
