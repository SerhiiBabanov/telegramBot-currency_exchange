package command.setting.roundResults.options;

import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class RoundToTwo extends EditCommand {
    protected static final String COMMAND_NAME = "/roundDigitSettingTwo";
    protected static final String BUTTON_TEXT = "2";
    protected static final String COMMAND_RESULT_TEXT = "Кількість знаків після коми";

    public RoundToTwo(SendCommand parentCommand) {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, parentCommand);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(2);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
