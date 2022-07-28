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
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        List<List<InlineKeyboardButton>> banksButtons = new ArrayList<>();
        InlineKeyboardButton round = new RoundToTree().getButton();
        round.setText(EmojiParser.parseToUnicode(":white_check_mark:" + round.getText()));
        banksButtons.add(List.of(new RoundToTwo().getButton()));
        banksButtons.add(List.of(round));
        banksButtons.add(List.of(new RoundToFour().getButton()));

        chatSetting.setRoundDigit(3);
        repository.add(chatSetting.getChatId(), chatSetting);

        return EditMessageText.builder()
                .text("Your round - 3")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(banksButtons).build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();
    }
}
