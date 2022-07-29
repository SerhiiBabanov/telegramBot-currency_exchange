package command.setting.bank.options;

import banksUtils.nbu.NBUUtils;
import model.EditCommand;
import model.ChatSetting;
import model.SendCommand;
import repository.Repository;

public class SetBankNBU extends EditCommand {
    public SetBankNBU(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/setNBU";
        buttonText = "НБУ";
        commandResultText = "Ваш банк - НБУ";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        chatSetting.setBank(new NBUUtils());
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
