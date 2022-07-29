package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt15 extends EditCommand {
    public SetReminderAt15(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt15";
        buttonText = "15";
        commandResultText = "Your time - 15";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(15);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
