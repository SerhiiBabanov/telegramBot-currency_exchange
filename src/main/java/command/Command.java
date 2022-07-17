package command;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public abstract class Command {
    protected String commandName;
    protected String buttonText;

    protected Command() {
    }

    public boolean canExecute(String commandName) {
        return this.commandName.equalsIgnoreCase(commandName);
    }
    public InlineKeyboardButton getButton(){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonText);
        button.setCallbackData(commandName);
        return button;
    }
}
