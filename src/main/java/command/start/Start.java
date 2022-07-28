package command.start;

import command.info.GetInfo;
import command.setting.Setting;
import model.ChatSetting;
import model.SendCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Start extends SendCommand {
    public Start() {
        commandName = "/start";
        buttonText = "/start";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(List.of(new GetInfo().getButton()));
        buttons.add(List.of(new Setting().getButton()));

        return SendMessage.builder()
                .text("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .chatId(chatSetting.getChatId())
                .build();
    }
}
