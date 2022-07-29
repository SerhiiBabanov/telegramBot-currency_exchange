package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt18 extends EditCommand {
    public SetReminderAt18(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt18";
        buttonText = "18";
        commandResultText = "Your time - 18";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(18);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
