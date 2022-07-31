package command.setting.roundResults.options;

import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class RoundToTree extends EditCommand {
    protected static final String COMMAND_NAME = "/roundDigitSetting3";
    protected static final String BUTTON_TEXT = "3";
    protected static final String COMMAND_RESULT_TEXT = "Кількість знаків після коми";
    protected static final String PARENT_COMMAND = "/roundDigitSetting";
    public RoundToTree() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(3);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
