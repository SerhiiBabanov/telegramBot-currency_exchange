package command.sendCommand;

import com.vdurmont.emoji.EmojiParser;
import command.editCommand.*;
import model.ChatSetting;
import model.Valute;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ValuteSetting extends SendCommand{
    public ValuteSetting() {
        commandName = "/valuteSetting";
        buttonText = "Valute setting";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
//
        List<List<InlineKeyboardButton>> settingsButtons = new ArrayList<>();
        settingsButtons.add(List.of(new USD().getButton()));
        settingsButtons.add(List.of(new CAN().getButton()));
        settingsButtons.add(List.of(new PZL().getButton()));

        settingsButtons = settingsButtons.stream()
                .flatMap(Collection::stream)
                .peek(button -> {
                    if ((chatSetting.getValutes().contains(Valute.valueOf(button.getText())))){
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
