package command.setting.roundResults.options;

import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class RoundToTwo extends EditCommand {
    public RoundToTwo(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/roundDigitSettingTwo";
        buttonText = "2";
        commandResultText = "Your round - 2";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(2);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
