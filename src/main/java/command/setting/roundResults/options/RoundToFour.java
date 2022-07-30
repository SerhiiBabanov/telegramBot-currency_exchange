package command.setting.roundResults.options;

import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class RoundToFour extends EditCommand {
    protected static final String COMMAND_NAME = "/roundDigitSettingFour";
    protected static final String BUTTON_TEXT = "4";
    protected static final String COMMAND_RESULT_TEXT = "Кількість знаків після коми";

    public RoundToFour(SendCommand parentCommand) {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, parentCommand);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(4);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
