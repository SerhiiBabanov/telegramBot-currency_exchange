package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class SetReminderAt17 extends EditCommand {
    protected static final String COMMAND_NAME = "/SetReminderAt17";
    protected static final String BUTTON_TEXT = "17";
    protected static final String COMMAND_RESULT_TEXT = "Ваш час сповіщень - 17 година";
    protected static final String PARENT_COMMAND = "/reminderSetting";

    public SetReminderAt17() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(17);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
