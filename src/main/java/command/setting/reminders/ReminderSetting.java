package command.setting.reminders;

import com.vdurmont.emoji.EmojiParser;
import command.setting.reminders.options.*;
import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import model.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ReminderSetting extends SendCommand {
    protected static final String COMMAND_NAME = "/reminderSetting";
    protected static final String BUTTON_TEXT = "Час сповіщень";
    protected static final String COMMAND_RESULT_TEXT = "EmptyText";

    public ReminderSetting() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT);
    }


    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> reminderButtons = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        int counterButtonsInRow = 0;
        for (EditCommand command : TelegramBot.getEditCommands()) {
            if (command.getParentCommand().getCommandName().equals(this.commandName)) {
                buttonsRow.add(command.getButton());
                counterButtonsInRow++;
                if (counterButtonsInRow == 3) {
                    reminderButtons.add(List.copyOf(buttonsRow));
                    buttonsRow.clear();
                    counterButtonsInRow = 0;
                }
            }
        }
        if (counterButtonsInRow != 0){
            reminderButtons.add(buttonsRow);
        }

        for (List<InlineKeyboardButton> row : reminderButtons
        ) {
            for (InlineKeyboardButton button : row
            ) {
                if (button.getCallbackData().equals("/SetReminderAt" + chatSetting.getReminderTime())){
                    button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                }

            }

        }
        return reminderButtons;
    }
}
