package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt9 extends EditCommand {
    public SetReminderAt9(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt9";
        buttonText = "9";
        commandResultText = "Your time - 9";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(9);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
