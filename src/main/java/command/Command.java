package command;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public abstract class Command {
    String commandName;
    public abstract boolean canExecute(String commandName);
    abstract InlineKeyboardButton getButton();
}
