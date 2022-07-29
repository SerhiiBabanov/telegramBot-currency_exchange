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
        buttonText = "Монобанк";
        commandResultText = "Ваш банк - Монобанк";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new MonobankUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
