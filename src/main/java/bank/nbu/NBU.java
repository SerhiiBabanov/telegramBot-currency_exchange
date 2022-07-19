package bank.nbu;

import model.Bank;
import model.Valute;

public class NBU extends Bank {
    @Override
    public double getValuteExchangeBay(Valute valute) {
        return 0;
    }

    @Override
    public double getValuteExchangeSell(Valute valute) {
        return 0;
    }

    @Override
    public String getButtonCallbackData() {
        return "/setNBU";
    }


    public String toString() {
        return "NBU";
    }
}
