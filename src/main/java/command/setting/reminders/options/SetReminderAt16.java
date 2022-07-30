package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt16 extends EditCommand {
    protected static final String COMMAND_NAME = "/SetReminderAt16";
    protected static final String BUTTON_TEXT = "16";
    protected static final String COMMAND_RESULT_TEXT = "Ваш час сповіщень - 16 година";

    public SetReminderAt16(SendCommand parentCommand) {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, parentCommand);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(16);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
