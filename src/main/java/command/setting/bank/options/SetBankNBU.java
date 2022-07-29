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
        commandResultText = "Ваш банк - НБУ";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new NBUUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
