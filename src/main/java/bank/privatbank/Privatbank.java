package bank.privatbank;

import model.Bank;

public class Privatbank extends Bank {
    @Override
    protected double getValuteExchange() {
        return 0;
    }
}
