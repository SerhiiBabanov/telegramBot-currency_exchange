package command.setting.roundResults.options;

import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class RoundToFour extends EditCommand {
    public RoundToFour(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/roundDigitSettingFour";
        buttonText = "4";
        commandResultText = "Your round - 4";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(4);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
