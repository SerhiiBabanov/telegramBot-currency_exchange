package command.setting.reminders.options;

import model.ChatSetting;
import model.EditCommand;
import model.SendCommand;
import repository.Repository;

public class SetReminderAt13 extends EditCommand {
    public SetReminderAt13(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/SetReminderAt13";
        buttonText = "13";
        commandResultText = "Your time - 13";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setReminderTime(13);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
