package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt11 extends EditCommand {
    public SetReminderAt11(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt11";
        buttonText = "11";
        commandResultText = "Your time - 11";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(11);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
