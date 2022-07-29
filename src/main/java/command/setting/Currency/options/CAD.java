package command.setting.Currency.options;

import model.EditCommand;
import model.ChatSetting;
import model.Currency;
import model.SendCommand;
import repository.Repository;

import java.util.List;

public class CAD extends EditCommand {
    public CAD(SendCommand parentCommand) {
        super(parentCommand);
        commandName = "/setCAD";
        buttonText = "CAD";
        commandResultText = "Зміни збережено";
    }


    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        List<Currency> currencies = chatSetting.getValutes();
        if (chatSetting.getValutes().contains(Currency.CAD)){
            currencies.remove(Currency.CAD);
        } else {
            currencies.add(Currency.CAD);
        }
        chatSetting.setValutes(currencies);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
