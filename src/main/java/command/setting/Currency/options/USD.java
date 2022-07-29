package command.setting.Currency.options;

import model.EditCommand;
import model.ChatSetting;
import model.Currency;
import model.SendCommand;
import repository.Repository;

import java.util.List;

public class USD extends EditCommand {
    public USD(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/setUSD";
        buttonText = "USD";
        commandResultText = "Зміни збережено";
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        List<Currency> currencies = chatSetting.getValutes();
        if (chatSetting.getValutes().contains(Currency.USD)){
            currencies.remove(Currency.USD);
        } else {
            currencies.add(Currency.USD);
        }
        chatSetting.setValutes(currencies);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
