package command.setting.Currency;

import com.vdurmont.emoji.EmojiParser;
import model.*;
import command.setting.Currency.options.CAD;
import command.setting.Currency.options.PLZ;
import command.setting.Currency.options.USD;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencySetting extends SendCommand {
    protected static final String COMMAND_NAME = "/valuteSetting";
    protected static final String BUTTON_TEXT = "Валюта";
    protected static final String COMMAND_RESULT_TEXT = "EmptyText";

    public CurrencySetting() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT);
    }
    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> settingsButtons = new ArrayList<>();
        for (EditCommand command: TelegramBot.getEditCommands()){
            if (command.getParentCommand().getCommandName().equals(this.commandName)){
                settingsButtons.add(List.of(command.getButton()));
            }
        }
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
