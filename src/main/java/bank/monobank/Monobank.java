package bank.monobank;

import model.Bank;
import model.Valute;

public class Monobank extends Bank {
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
        return "/setMonobank";
    }


    public String toString(Valute valute) {
        return "Monobank";
    }

}
