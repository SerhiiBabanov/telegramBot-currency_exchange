package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt11 extends EditCommand {
    protected static final String COMMAND_NAME = "/SetReminderAt11";
    protected static final String BUTTON_TEXT = "11";
    protected static final String COMMAND_RESULT_TEXT = "Ваш час сповіщень - 11 година";

    public SetReminderAt11(SendCommand parentCommand) {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, parentCommand);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(11);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
