package command.setting.bank.options;

import banksUtils.monobank.MonobankUtils;
import com.vdurmont.emoji.EmojiParser;
import model.EditCommand;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class SetBankMonobank extends EditCommand {
    public SetBankMonobank() {
        commandName = "/setMonobank";
        buttonText = "Set Bank Monobank";
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {

        List<List<InlineKeyboardButton>> banksButtons = new ArrayList<>();
        InlineKeyboardButton monobank = new SetBankMonobank().getButton();
        monobank.setText(EmojiParser.parseToUnicode(":white_check_mark:" + monobank.getText()));
        banksButtons.add(List.of(monobank));
        banksButtons.add(List.of(new SetBankPrivatbank().getButton()));
        banksButtons.add(List.of(new SetBankNBU().getButton()));

        chatSetting.setBank(new MonobankUtils());
        repository.add(chatSetting.getChatId(), chatSetting);

        return EditMessageText.builder()
                .text("Your bank - Monobank")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(banksButtons).build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();
    }
}
