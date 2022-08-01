package command.setting.roundResults.options;

import command.setting.roundResults.RoundSetting;
import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class RoundToTree extends EditCommand {
    protected static final String COMMAND_NAME = "/roundDigitSetting3";
    protected static final String BUTTON_TEXT = "3";
    protected static final String COMMAND_RESULT_TEXT = "Кількість знаків після коми";
    protected static final String PARENT_COMMAND = RoundSetting.COMMAND_NAME;

    public RoundToTree() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(3);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
