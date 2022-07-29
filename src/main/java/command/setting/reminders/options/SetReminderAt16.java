package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt16 extends EditCommand {
    public SetReminderAt16(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt16";
        buttonText = "16";
        commandResultText = "Your time - 16";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(16);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
