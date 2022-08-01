package command.setting.roundResults.options;

import command.setting.roundResults.RoundSetting;
import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class RoundToTwo extends EditCommand {
    protected static final String COMMAND_NAME = "/roundDigitSetting2";
    protected static final String BUTTON_TEXT = "2";
    protected static final String COMMAND_RESULT_TEXT = "Кількість знаків після коми";
    protected static final String PARENT_COMMAND = RoundSetting.COMMAND_NAME;

    public RoundToTwo() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(2);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
