package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAtNone extends EditCommand {
    public SetReminderAtNone(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAtNone";
        buttonText = "Turn off reminder";
        commandResultText = "Reminder disabled";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(25);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
