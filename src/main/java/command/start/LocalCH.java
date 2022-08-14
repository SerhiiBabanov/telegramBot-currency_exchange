package command.start;

import model.ChatSetting;
import model.Command;
import model.SendCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static model.TelegramBot.getSendCommands;

public class LocalCH extends SendCommand {
    public LocalCH(String commandName, String buttonText, String commandResultText, String parentCommand) {
        super(commandName, buttonText, commandResultText, parentCommand);
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.Resource", new Locale("US"));
//        Stream.concat(getEditCommands().stream(), getSendCommands().stream())
//                .forEach(command -> {
//                    command.setCommandResultText("");
//                    command.setButtonText("");
//                });
        String buttonText = bundle.getString("/start" + "buttonText".toUpperCase());
        String commandResultText = bundle.getString("/start" + "commandResultText".toUpperCase());
        for (Command command: getSendCommands()
        ) {
            if (command.canExecute("/start")){
                command.setButtonText(buttonText);
                command.setCommandResultText(commandResultText);
            }

        }
        return super.execute(chatSetting);
    }

    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        return null;
    }

    @Override
    public InlineKeyboardButton getBackButton() {
        return null;
    }
}
