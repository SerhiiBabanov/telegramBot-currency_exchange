package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt12 extends EditCommand {
    public SetReminderAt12(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt12";
        buttonText = "12";
        commandResultText = "Your time - 12";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(12);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
