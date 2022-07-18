package bank.nbu;

import model.Bank;

public class NBU extends Bank {
    @Override
    protected double getValuteExchange() {
        return 0;
    }

    @Override
    public String toString() {
        return "NBU";
    }
}
