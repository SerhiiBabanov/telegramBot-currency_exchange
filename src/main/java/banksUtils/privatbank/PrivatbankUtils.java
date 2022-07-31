package banksUtils.privatbank;

import banksUtils.BankHttpUtils;
import com.google.gson.reflect.TypeToken;
import model.Bank;
import model.Exchange;

import java.lang.reflect.Type;
import java.util.List;

public class PrivatbankUtils extends Bank {
    private static final String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    private static final Type typeToken = new TypeToken<List<Exchange>>() {
    }.getType();
    private static List<Exchange> exchanges;

    static {
        updateExchangeList();
    }

    public static void updateExchangeList() {
        exchanges = BankHttpUtils.getExchangeList(URL, typeToken);
    }

    @Override
    public List<Exchange> getExchangeList() {
        return List.copyOf(exchanges);
    }

    @Override
    public String getButtonCallbackData() {
        return "/setPrivatbank";
    }

    @Override
    public String toString() {
        return "/privatbank";
    }
}
