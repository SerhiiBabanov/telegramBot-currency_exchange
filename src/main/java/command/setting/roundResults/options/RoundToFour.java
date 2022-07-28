package command.setting.roundResults.options;

import banksUtils.privatbank.PrivatbankUtils;
import com.vdurmont.emoji.EmojiParser;
import command.setting.bank.options.SetBankMonobank;
import command.setting.bank.options.SetBankNBU;
import command.setting.bank.options.SetBankPrivatbank;
import model.EditCommand;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class RoundToFour extends EditCommand {
    public RoundToFour() {
        commandName = "/roundDigitSettingFour";
        buttonText = "4";
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        List<List<InlineKeyboardButton>> banksButtons = new ArrayList<>();
        InlineKeyboardButton round = new RoundToFour().getButton();
        round.setText(EmojiParser.parseToUnicode(":white_check_mark:" + round.getText()));
        banksButtons.add(List.of(new RoundToTwo().getButton()));
        banksButtons.add(List.of(new RoundToTree().getButton()));
        banksButtons.add(List.of(round));

        chatSetting.setRoundDigit(4);
        repository.add(chatSetting.getChatId(), chatSetting);

        return EditMessageText.builder()
                .text("Your round - 4")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(banksButtons).build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();

    }
}
