package command.setting.roundResults;


import com.google.gson.Gson;
import com.vdurmont.emoji.EmojiParser;
import model.ChatSetting;
import model.SendCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RoundSetting extends SendCommand {
    protected static final String COMMAND_NAME = "/roundDigitSetting";
    protected static final String BUTTON_TEXT = "Кількість знаків після коми";
    protected static final String COMMAND_RESULT_TEXT = "EmptyText";

    public RoundSetting() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT);
    }


    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        Gson gson = new Gson();
        return settingsButtons.stream()
                .flatMap(Collection::stream)
                .map(button -> gson.fromJson(gson.toJson(button), InlineKeyboardButton.class))
                .peek(button -> {
                    if (button.getText().equals(String.valueOf(chatSetting.getRoundDigit()))) {
                        button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                    }
                })
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }


}
