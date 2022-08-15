package command.setting.languages.options;

import command.setting.languages.LanguagesSetting;
import model.ChatSetting;
import model.EditCommand;
import model.TelegramBot;
import repository.Repository;

import java.util.Locale;

public class LanguageUA extends EditCommand {
    public static final String COMMAND_NAME = "/languagesSettingUA";
    protected static final String BUTTON_TEXT = "Українська";
    protected static final String COMMAND_RESULT_TEXT = "Зміни збережено";
    protected static final String PARENT_COMMAND = LanguagesSetting.COMMAND_NAME;
    public LanguageUA() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        Locale localeUA = new Locale("ua");
        chatSetting.setLocale(localeUA);
        repository.add(chatSetting.getChatId(), chatSetting);
        TelegramBot.changeLocal(localeUA);
    }
}
