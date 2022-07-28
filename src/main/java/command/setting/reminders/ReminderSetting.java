package command.setting.reminders;

import com.vdurmont.emoji.EmojiParser;
import command.setting.reminders.options.*;
import command.setting.roundResults.options.RoundToFour;
import command.setting.roundResults.options.RoundToTree;
import command.setting.roundResults.options.RoundToTwo;
import model.ChatSetting;
import model.SendCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReminderSetting extends SendCommand {
    public ReminderSetting() {
        commandName = "/reminderSetting";
        buttonText = "reminderSetting";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> reminderButtons = new ArrayList<>();
        reminderButtons.add(List.of(
                new SetReminderAt9().getButton(),
                new SetReminderAt10().getButton(),
                new SetReminderAt11().getButton(),
                new SetReminderAt12().getButton()));
        reminderButtons.add(List.of(
                new SetReminderAt13().getButton(),
                new SetReminderAt14().getButton(),
                new SetReminderAt15().getButton(),
                new SetReminderAt16().getButton()));
        reminderButtons.add(List.of(
                new SetReminderAt17().getButton(),
                new SetReminderAt18().getButton(),
                new SetReminderAtNone().getButton()
                ));
        for (List<InlineKeyboardButton> row: reminderButtons
             ) {
            for (InlineKeyboardButton button: row
                 ) {
                if (button.getText().equals(String.valueOf(chatSetting.getReminderTime()))){
                    button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                }
                if (chatSetting.getReminderTime() == 25 & button.getText().equals("Turn off reminder")){
                    button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                }

            }

        }

        return SendMessage.builder()
                .text(buttonText)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(reminderButtons).build())
                .chatId(chatSetting.getChatId())
                .build();
    }
}
