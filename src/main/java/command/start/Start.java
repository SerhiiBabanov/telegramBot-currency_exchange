package command.start;

import com.vdurmont.emoji.EmojiParser;
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
    protected static final String COMMAND_NAME = "/start";
    protected static final String BUTTON_TEXT = "/start";
    protected static final String COMMAND_RESULT_TEXT = "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют";

    public Start() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT);
    }
    public InlineKeyboardButton getHomeButton(){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(":house:" + "Додому"));
        button.setCallbackData(commandName);
        return button;
    }
    @Override
    public SendMessage execute(ChatSetting chatSetting) {

        return SendMessage.builder()
                .text(commandResultText)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(getKeyboard(chatSetting)).build())
                .chatId(chatSetting.getChatId())
                .build();
    }

    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(List.of(new GetInfo().getButton()));
        buttons.add(List.of(new Setting().getButton()));
        return buttons;
    }

    @Override
    public InlineKeyboardButton getBackButton() {
        return getHomeButton();
    }
}
