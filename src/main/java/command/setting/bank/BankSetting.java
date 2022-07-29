package command.setting.bank;

import com.vdurmont.emoji.EmojiParser;
import command.setting.bank.options.SetBankMonobank;
import command.setting.bank.options.SetBankNBU;
import command.setting.bank.options.SetBankPrivatbank;
import model.ChatSetting;
import model.SendCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BankSetting extends SendCommand {
    public BankSetting() {
        commandName = "/bankSetting";
        buttonText = "Банк";
    }

    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> settingsButtons = new ArrayList<>();
        settingsButtons.add(List.of(new SetBankMonobank(this).getButton()));
        settingsButtons.add(List.of(new SetBankPrivatbank(this).getButton()));
        settingsButtons.add(List.of(new SetBankNBU(this).getButton()));

        settingsButtons = settingsButtons.stream()
                .flatMap(Collection::stream)
                .peek(button -> {
                    if (button.getCallbackData().equals(chatSetting.getBank().getButtonCallbackData())) {
                        button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                    }
                })
                .map(Arrays::asList)
                .collect(Collectors.toList());

        return settingsButtons;
    }

}
