package model;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public abstract class Command {
    protected final String commandName;
    protected final String buttonText;
    protected final String commandResultText;
    protected final String parentCommand;

    protected Command(String commandName, String buttonText, String commandResultText, String parentCommand) {
        this.commandName = commandName;
        this.buttonText = buttonText;
        this.commandResultText = commandResultText;
        this.parentCommand = parentCommand;
    }

    public String getCommandName() {
        return commandName;
    }
    public String getParentCommand() {
        return parentCommand;
    }


    public boolean canExecute(String commandName) {
        return this.commandName.equalsIgnoreCase(commandName);
    }

    public  InlineKeyboardButton getButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonText);
        button.setCallbackData(commandName);
        return button;
    }
}
