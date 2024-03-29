package command.setting.bank.options;

import banksUtils.monobank.MonobankUtils;
import command.setting.bank.BankSetting;
import model.ChatSetting;
import model.EditCommand;
import repository.Repository;

public class SetBankMonobank extends EditCommand {
    protected static final String COMMAND_NAME = "/setMonobank";
    protected static final String BUTTON_TEXT = "Монобанк";
    protected static final String COMMAND_RESULT_TEXT = "Ваш банк - Монобанк";
    protected static final String PARENT_COMMAND = BankSetting.COMMAND_NAME;

    public SetBankMonobank() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new MonobankUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
