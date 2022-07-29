package command.setting.bank.options;

import banksUtils.privatbank.PrivatbankUtils;
import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class SetBankPrivatbank extends EditCommand {
    public SetBankPrivatbank(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/setPrivatbank";
        buttonText = "ПриватБанк";
        commandResultText = "Ваш банк - ПриватБанк";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new PrivatbankUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
