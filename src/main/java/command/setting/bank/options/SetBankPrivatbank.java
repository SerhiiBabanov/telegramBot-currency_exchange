package command.setting.bank.options;

import banksUtils.nbu.NBUUtils;
import banksUtils.privatbank.PrivatbankUtils;
import com.vdurmont.emoji.EmojiParser;
import command.setting.bank.BankSetting;
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
        commandResultText = "Ваш банк - ПриватБанк";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new PrivatbankUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
