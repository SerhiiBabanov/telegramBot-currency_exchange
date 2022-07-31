package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class SetReminderAt14 extends EditCommand {
    protected static final String COMMAND_NAME = "/SetReminderAt14";
    protected static final String BUTTON_TEXT = "14";
    protected static final String COMMAND_RESULT_TEXT = "Ваш час сповіщень - 14 година";
    protected static final String PARENT_COMMAND = "/reminderSetting";

    public SetReminderAt14() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(14);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
