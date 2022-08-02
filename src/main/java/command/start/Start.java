package command.start;

import com.vdurmont.emoji.EmojiParser;
import command.info.GetInfo;
import command.setting.Setting;
import model.ChatSetting;
import model.SendCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Start extends SendCommand {
    public static final String COMMAND_NAME = "/start";
    protected static final String BUTTON_TEXT = "/start";
    protected static final String COMMAND_RESULT_TEXT = "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют";
    protected static final String PARENT_COMMAND = COMMAND_NAME;
    public Start() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }
    public static InlineKeyboardButton getHomeButton(){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(":house:" + "Додому"));
        button.setCallbackData(COMMAND_NAME);
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
        return settingsButtons;
    }

    @Override
    protected void setSettingsButtons() {
        settingsButtons.add(List.of(new GetInfo().getButton()));
        settingsButtons.add(List.of(new Setting().getButton()));

    }

    @Override
    public InlineKeyboardButton getBackButton() {
        return getHomeButton();
    }
}
