package command.setting.roundResults;


import com.vdurmont.emoji.EmojiParser;
import model.SendCommand;
import command.setting.roundResults.options.RoundToFour;
import command.setting.roundResults.options.RoundToTree;
import command.setting.roundResults.options.RoundToTwo;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RoundSetting extends SendCommand {
    public RoundSetting() {
        commandName = "/roundDigitSetting";
        buttonText = "Кількість знаків після коми";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {

        List<List<InlineKeyboardButton>> settingsButtons = new ArrayList<>();
        settingsButtons.add(List.of(new RoundToTwo().getButton()));
        settingsButtons.add(List.of(new RoundToTree().getButton()));
        settingsButtons.add(List.of(new RoundToFour().getButton()));

        settingsButtons = settingsButtons.stream()
                .flatMap(Collection::stream)
                .peek(button -> {
                    if (button.getText().equals(String.valueOf(chatSetting.getRoundDigit()))){
                        button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                    }
                })
                .map(Arrays::asList)
                .collect(Collectors.toList());

        return SendMessage.builder()
                .text(buttonText)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(settingsButtons).build())
                .chatId(chatSetting.getChatId())
                .build();
    }
}
