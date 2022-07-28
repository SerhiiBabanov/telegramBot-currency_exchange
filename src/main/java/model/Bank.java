package model;

import java.util.List;

public abstract class Bank {
    public abstract List<Exchange> getExchangeList();
    public abstract String getButtonCallbackData();
}
