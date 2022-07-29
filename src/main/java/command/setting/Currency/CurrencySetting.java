package command.setting.Currency;

import com.vdurmont.emoji.EmojiParser;
import model.SendCommand;
import command.setting.Currency.options.CAD;
import command.setting.Currency.options.PLZ;
import command.setting.Currency.options.USD;
import model.ChatSetting;
import model.Currency;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencySetting extends SendCommand {
    public CurrencySetting() {
        commandName = "/valuteSetting";
        buttonText = "Валюта";
    }
    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> settingsButtons = new ArrayList<>();
        settingsButtons.add(List.of(new USD().getButton()));
        settingsButtons.add(List.of(new CAD().getButton()));
        settingsButtons.add(List.of(new PLZ().getButton()));

        settingsButtons = settingsButtons.stream()
                .flatMap(Collection::stream)
                .peek(button -> {
                    if ((chatSetting.getValutes().contains(Currency.valueOf(button.getText())))){
                        button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                    }
                })
                .map(Arrays::asList)
                .collect(Collectors.toList());
        return settingsButtons;
    }
}
