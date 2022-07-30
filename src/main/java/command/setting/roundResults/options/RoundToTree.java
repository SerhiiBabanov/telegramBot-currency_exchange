package command.setting.roundResults.options;

import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class RoundToTree extends EditCommand {
    protected static final String COMMAND_NAME = "/roundDigitSettingTree";
    protected static final String BUTTON_TEXT = "3";
    protected static final String COMMAND_RESULT_TEXT = "Кількість знаків після коми";

    public RoundToTree(SendCommand parentCommand) {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, parentCommand);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(3);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
