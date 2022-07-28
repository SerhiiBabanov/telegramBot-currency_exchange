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
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        List<List<InlineKeyboardButton>> banksButtons = new ArrayList<>();
        InlineKeyboardButton round = new RoundToTwo().getButton();
        round.setText(EmojiParser.parseToUnicode(":white_check_mark:" + round.getText()));
        banksButtons.add(List.of(round));
        banksButtons.add(List.of(new RoundToTree().getButton()));
        banksButtons.add(List.of(new RoundToFour().getButton()));

        chatSetting.setRoundDigit(2);
        repository.add(chatSetting.getChatId(), chatSetting);

        return EditMessageText.builder()
                .text("Your round - 2")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(banksButtons).build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();
    }
}
