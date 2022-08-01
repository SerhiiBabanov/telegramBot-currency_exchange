package command.setting.reminders.options;

import command.setting.reminders.ReminderSetting;
import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class SetReminderAt13 extends EditCommand {
    protected static final String COMMAND_NAME = "/SetReminderAt13";
    protected static final String BUTTON_TEXT = "13";
    protected static final String COMMAND_RESULT_TEXT = "Ваш час сповіщень - 13 година";
    protected static final String PARENT_COMMAND = ReminderSetting.COMMAND_NAME;

    public SetReminderAt13() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(13);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
