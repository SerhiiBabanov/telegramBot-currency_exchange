package command.setting;

import com.vdurmont.emoji.EmojiParser;
import command.setting.Currency.CurrencySetting;
import command.setting.bank.BankSetting;
import command.setting.reminders.ReminderSetting;
import command.setting.roundResults.RoundSetting;
import command.start.Start;
import model.ChatSetting;
import model.SendCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Setting extends SendCommand {
    protected static final String COMMAND_NAME = "/setting";
    protected static final String BUTTON_TEXT = "Налаштування";
    protected static final String COMMAND_RESULT_TEXT = "EmptyText";

    public Setting() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT);
    }
    @Override
    public InlineKeyboardButton getBackButton(){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(":back:" + "Назад"));
        button.setCallbackData(new Start().getCommandName());
        return button;
    }
    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        List<List<InlineKeyboardButton>> settingsButtons = new ArrayList<>();
        settingsButtons.add(List.of(new RoundSetting().getButton()));
        settingsButtons.add(List.of(new BankSetting().getButton()));
        settingsButtons.add(List.of(new CurrencySetting().getButton()));
        settingsButtons.add(List.of(new ReminderSetting().getButton()));
        settingsButtons.add(List.of(new Start().getHomeButton(), getBackButton()));
        return settingsButtons;
    }
}
