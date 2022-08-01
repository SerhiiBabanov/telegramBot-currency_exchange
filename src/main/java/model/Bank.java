package model;

import java.util.List;

public abstract class Bank {
    protected final String commandName;
    protected final String buttonText;
    protected final String commandResultText;

    protected Bank(String commandName, String buttonText, String commandResultText) {
        this.commandName = commandName;
        this.buttonText = buttonText;
        this.commandResultText = commandResultText;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandResultText() {
        return commandResultText;
    }

    public abstract List<Exchange> getExchangeList();

}
