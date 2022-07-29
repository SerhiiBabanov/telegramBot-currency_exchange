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
        commandResultText = "Your round - 4";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(4);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
