package command.setting.bank.options;

import banksUtils.privatbank.PrivatbankUtils;
import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class SetBankPrivatbank extends EditCommand {
    protected static final String COMMAND_NAME = "/setPrivatbank";
    protected static final String BUTTON_TEXT = "ПриватБанк";
    protected static final String COMMAND_RESULT_TEXT = "Ваш банк - ПриватБанк";
    protected static final String PARENT_COMMAND = "/bankSetting";

    public SetBankPrivatbank() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new PrivatbankUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
