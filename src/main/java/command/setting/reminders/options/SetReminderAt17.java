package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt17 extends EditCommand {
    public SetReminderAt17(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt17";
        buttonText = "17";
        commandResultText = "Your time - 17";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(17);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
