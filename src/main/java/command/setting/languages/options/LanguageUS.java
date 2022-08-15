package command.setting.languages.options;

import command.setting.languages.LanguagesSetting;
import model.ChatSetting;
import model.EditCommand;
import model.TelegramBot;
import repository.Repository;

import java.util.Locale;

public class LanguageUS extends EditCommand {
    public static final String COMMAND_NAME = "/languagesSettingUS";
    protected static final String BUTTON_TEXT = "English";
    protected static final String COMMAND_RESULT_TEXT = "EmptyText";
    protected static final String PARENT_COMMAND = LanguagesSetting.COMMAND_NAME;
    public LanguageUS() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        Locale localeUS = new Locale("us");
        chatSetting.setLocale(localeUS);
        repository.add(chatSetting.getChatId(), chatSetting);
        TelegramBot.changeLocal(localeUS);
    }
}
