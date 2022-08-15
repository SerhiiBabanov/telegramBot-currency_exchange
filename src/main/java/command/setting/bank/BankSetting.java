package command.setting.bank;

import com.google.gson.Gson;
import com.vdurmont.emoji.EmojiParser;
import command.setting.Setting;
import command.start.Start;
import model.ChatSetting;
import model.SendCommand;
import model.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankSetting extends SendCommand {
    public static final String COMMAND_NAME = "/bankSetting";
    protected static final String BUTTON_TEXT = "Банк";
    protected static final String COMMAND_RESULT_TEXT = "EmptyText";
    protected static final String PARENT_COMMAND = Setting.COMMAND_NAME;

    public BankSetting() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        Gson gson = new Gson();
        return Stream.concat(
                settingsButtons.stream()
                        .flatMap(Collection::stream)
                        .map(button -> gson.fromJson(gson.toJson(button), InlineKeyboardButton.class))
                        .peek(button -> {
                            if (button.getCallbackData().equals(chatSetting.getBank().getCommandName())) {
                                button.setText(EmojiParser.parseToUnicode(":white_check_mark:" + button.getText()));
                            }
                        })
                        .map(Arrays::asList),
                Stream.of(List.of(Start.getHomeButton(), getBackButton()))).collect(Collectors.toList());

    }

    @Override
    public InlineKeyboardButton getBackButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(":back:" + TelegramBot.getBackButtonText()));
        button.setCallbackData(PARENT_COMMAND);
        return button;
    }
}
