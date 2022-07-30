package command.setting.bank.options;

import banksUtils.nbu.NBUUtils;
import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class SetBankNBU extends EditCommand {
    protected static final String COMMAND_NAME = "/setNBU";
    protected static final String BUTTON_TEXT = "НБУ";
    protected static final String COMMAND_RESULT_TEXT = "Ваш банк - НБУ";

    public SetBankNBU(SendCommand parentCommand) {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, parentCommand);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new NBUUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
