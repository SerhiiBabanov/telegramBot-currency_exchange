package command.setting.reminders.options;

import command.setting.reminders.ReminderSetting;
import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class SetReminderAt12 extends EditCommand {
    protected static final String COMMAND_NAME = "/SetReminderAt12";
    protected static final String BUTTON_TEXT = "12";
    protected static final String COMMAND_RESULT_TEXT = "Ваш час сповіщень - 12 година";
    protected static final String PARENT_COMMAND = ReminderSetting.COMMAND_NAME;

    public SetReminderAt12() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(12);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
