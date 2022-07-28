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

public class SetReminderAt12 extends EditCommand {
    public SetReminderAt12() {
        commandName = "/SetReminderAt12";
        buttonText = "12";
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        List<List<InlineKeyboardButton>> reminderButtons = new ArrayList<>();
        InlineKeyboardButton check = new SetReminderAt12().getButton();
        check.setText(EmojiParser.parseToUnicode(":white_check_mark:" + check.getText()));
        reminderButtons.add(List.of(
                new SetReminderAt9().getButton(),
                new SetReminderAt10().getButton(),
                new SetReminderAt11().getButton(),
                check));
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

        chatSetting.setReminderTime(12);
        repository.add(chatSetting.getChatId(), chatSetting);

        return EditMessageText.builder()
                .text("Your time - 12")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(reminderButtons).build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();
    }
}
