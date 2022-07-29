package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt14 extends EditCommand {
    public SetReminderAt14(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt14";
        buttonText = "14";
        commandResultText = "Your time - 14";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(14);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
