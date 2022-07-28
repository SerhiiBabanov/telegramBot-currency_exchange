package command.setting.bank.options;

import banksUtils.nbu.NBUUtils;
import banksUtils.privatbank.PrivatbankUtils;
import com.vdurmont.emoji.EmojiParser;
import model.EditCommand;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class SetBankPrivatbank extends EditCommand {
    public SetBankPrivatbank() {
        commandName = "/setPrivatbank";
        buttonText = "ПриватБанк";
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        List<List<InlineKeyboardButton>> banksButtons = new ArrayList<>();
        InlineKeyboardButton privatBank = new SetBankPrivatbank().getButton();
        privatBank.setText(EmojiParser.parseToUnicode(":white_check_mark:" + privatBank.getText()));
        banksButtons.add(List.of(new SetBankMonobank().getButton()));
        banksButtons.add(List.of(privatBank));
        banksButtons.add(List.of(new SetBankNBU().getButton()));

        chatSetting.setBank(new PrivatbankUtils());
        repository.add(chatSetting.getChatId(), chatSetting);

        return EditMessageText.builder()
                .text("Ваш банк - ПриватБанк")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(banksButtons).build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();
    }
}
