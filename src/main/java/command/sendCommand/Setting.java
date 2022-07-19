package command.sendCommand;

import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Setting extends SendCommand {
    public Setting() {
        commandName = "/setting";
        buttonText = "Налаштування";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> settingsButtons = new ArrayList<>();
        settingsButtons.add(List.of(new RoundDigitSetting().getButton()));
        settingsButtons.add(List.of(new BankSetting().getButton()));
        settingsButtons.add(List.of(new ValuteSetting().getButton()));

        return SendMessage.builder()
                .text(buttonText)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(settingsButtons).build())
                .chatId(chatSetting.getChatId())
                .build();
    }
}
