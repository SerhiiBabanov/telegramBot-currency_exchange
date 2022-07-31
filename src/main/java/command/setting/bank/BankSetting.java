package command.setting.bank;

import com.google.gson.Gson;
import com.vdurmont.emoji.EmojiParser;
import command.setting.bank.options.SetBankMonobank;
import command.setting.bank.options.SetBankNBU;
import command.setting.bank.options.SetBankPrivatbank;
import model.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;
import java.util.stream.Collectors;

public class BankSetting extends SendCommand {
    protected static final String COMMAND_NAME = "/bankSetting";
    protected static final String BUTTON_TEXT = "Банк";
    protected static final String COMMAND_RESULT_TEXT = "EmptyText";

    public BankSetting() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT);
    }

    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        Gson gson = new Gson();
        return settingsButtons.stream()
                .flatMap(Collection::stream)
                .map(button -> gson.fromJson(gson.toJson(button), InlineKeyboardButton.class))
                .peek(button -> {
                    if (button.getCallbackData().equals(chatSetting.getBank().getButtonCallbackData())) {
                        button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                    }
                })
                .map(Arrays::asList)
                .collect(Collectors.toList());

    }

}
