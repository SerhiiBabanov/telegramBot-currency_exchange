package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt10 extends EditCommand {
    public SetReminderAt10(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt10";
        buttonText = "10";
        commandResultText = "Your time - 10";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(10);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
