package model;

import java.util.List;

public abstract class Bank {
    protected final String commandName;
    protected final String name;

    protected Bank(String commandName, String name) {
        this.commandName = commandName;
        this.name = name;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getName() {
        return name;
    }

    public abstract List<Exchange> getExchangeList();

}
