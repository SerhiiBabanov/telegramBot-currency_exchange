package command.setting.Currency.options;

import model.ChatSetting;
import model.Currency;
import model.EditCommand;
import repository.Repository;

import java.util.List;

public class CAD extends EditCommand {
    protected static final String COMMAND_NAME = "/setCAD";
    protected static final String BUTTON_TEXT = "CAD";
    protected static final String COMMAND_RESULT_TEXT = "Зміни збережено";
    protected static final String PARENT_COMMAND = "/valuteSetting";

    public CAD() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }


    @Override
    public void setSetting(ChatSetting chatSetting, Repository repository) {
        List<Currency> currencies = chatSetting.getValutes();
        if (chatSetting.getValutes().contains(Currency.CAD)) {
            currencies.remove(Currency.CAD);
        } else {
            currencies.add(Currency.CAD);
        }
        chatSetting.setValutes(currencies);
        repository.add(chatSetting.getChatId(), chatSetting);
    }
}
