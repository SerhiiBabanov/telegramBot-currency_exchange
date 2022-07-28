package command.setting.bank.options;

import banksUtils.monobank.MonobankUtils;
import banksUtils.nbu.NBUUtils;
import com.vdurmont.emoji.EmojiParser;
import model.EditCommand;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class SetBankNBU extends EditCommand {
    public SetBankNBU() {
        commandName = "/setNBU";
        buttonText = "НБУ";
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        List<List<InlineKeyboardButton>> banksButtons = new ArrayList<>();
        InlineKeyboardButton nbu = new SetBankNBU().getButton();
        nbu.setText(EmojiParser.parseToUnicode(":white_check_mark:" + nbu.getText()));
        banksButtons.add(List.of(new SetBankMonobank().getButton()));
        banksButtons.add(List.of(new SetBankPrivatbank().getButton()));
        banksButtons.add(List.of(nbu));

        chatSetting.setBank(new NBUUtils());
        repository.add(chatSetting.getChatId(), chatSetting);

        return EditMessageText.builder()
                .text("Ваш банк - НБУ")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(banksButtons).build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();
    }
}
