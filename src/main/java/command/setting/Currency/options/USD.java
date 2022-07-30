package command.setting.Currency.options;

import model.EditCommand;
import model.ChatSetting;
import model.Currency;
import model.SendCommand;
import repository.Repository;

import java.util.List;

public class USD extends EditCommand {
    protected static final String COMMAND_NAME = "/setUSD";
    protected static final String BUTTON_TEXT = "USD";
    protected static final String COMMAND_RESULT_TEXT = "Зміни збережено";

    public USD(SendCommand parentCommand) {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, parentCommand);
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
