package command.setting.bank.options;

import banksUtils.monobank.MonobankUtils;
import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class SetBankMonobank extends EditCommand {
    public SetBankMonobank(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/setMonobank";
        buttonText = "Монобанк";
        commandResultText = "Ваш банк - Монобанк";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new MonobankUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
