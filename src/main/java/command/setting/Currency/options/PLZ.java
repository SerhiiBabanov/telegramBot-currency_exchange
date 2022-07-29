package command.setting.Currency.options;

import model.EditCommand;
import model.ChatSetting;
import model.Currency;
import model.SendCommand;
import repository.Repository;

import java.util.List;

public class PLZ extends EditCommand {
    public PLZ(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/setPLZ";
        buttonText = "PLZ";
        commandResultText = "Зміни збережено";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        List<Currency> currencies = chatSetting.getValutes();
        if (chatSetting.getValutes().contains(Currency.PLZ)){
            currencies.remove(Currency.PLZ);
        } else {
            currencies.add(Currency.PLZ);
        }
        chatSetting.setValutes(currencies);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
