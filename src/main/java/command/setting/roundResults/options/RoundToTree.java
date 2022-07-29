package command.setting.roundResults.options;

import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class RoundToTree extends EditCommand {
    public RoundToTree(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/roundDigitSettingTree";
        buttonText = "3";
        commandResultText = "Your round - 3";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(3);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
