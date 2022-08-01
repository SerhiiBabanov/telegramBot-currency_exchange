package command.setting.Currency.options;

import command.setting.Currency.CurrencySetting;
import model.ChatSetting;
import model.Currency;
import model.EditCommand;
import repository.Repository;

import java.util.List;

public class PLZ extends EditCommand {
    protected static final String COMMAND_NAME = "/setPLZ";
    protected static final String BUTTON_TEXT = "PLZ";
    protected static final String COMMAND_RESULT_TEXT = "Зміни збережено";
    protected static final String PARENT_COMMAND = CurrencySetting.COMMAND_NAME;

    public PLZ() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }

    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        List<Currency> currencies = chatSetting.getValutes();
        if (chatSetting.getValutes().contains(Currency.PLZ)) {
            currencies.remove(Currency.PLZ);
        } else {
            currencies.add(Currency.PLZ);
        }
        chatSetting.setValutes(currencies);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
