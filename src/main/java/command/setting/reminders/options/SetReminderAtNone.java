package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAtNone extends EditCommand {
    protected static final String COMMAND_NAME = "/SetReminderAt25";
    protected static final String BUTTON_TEXT = "Вимкнути сповіщення";
    protected static final String COMMAND_RESULT_TEXT = "Сповіщення вимкнені";
    protected static final String PARENT_COMMAND = "/reminderSetting";
    public SetReminderAtNone() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(25);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
