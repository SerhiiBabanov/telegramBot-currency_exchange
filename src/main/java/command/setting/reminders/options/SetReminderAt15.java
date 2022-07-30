package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt15 extends EditCommand {
    protected static final String COMMAND_NAME = "/SetReminderAt15";
    protected static final String BUTTON_TEXT = "15";
    protected static final String COMMAND_RESULT_TEXT = "Ваш час сповіщень - 15 година";

    public SetReminderAt15(SendCommand parentCommand) {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, parentCommand);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(15);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
