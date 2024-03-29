package command.setting.roundResults.options;

import command.setting.roundResults.RoundSetting;
import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class RoundToFour extends EditCommand {
    protected static final String COMMAND_NAME = "/roundDigitSetting4";
    protected static final String BUTTON_TEXT = "4";
    protected static final String COMMAND_RESULT_TEXT = "Кількість знаків після коми";
    protected static final String PARENT_COMMAND = RoundSetting.COMMAND_NAME;

    public RoundToFour() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setRoundDigit(4);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
