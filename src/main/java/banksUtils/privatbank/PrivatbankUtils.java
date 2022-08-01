package banksUtils.privatbank;

import banksUtils.BankHttpUtils;
import com.google.gson.reflect.TypeToken;
import model.Bank;
import model.Exchange;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrivatbankUtils extends Bank {
    private static final String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    public static final String COMMAND_NAME = "/setPrivatbank";
    protected static final String BUTTON_TEXT = "Приватбанк";
    protected static final String COMMAND_RESULT_TEXT = "Курс в ПриватБанк";

    private static final Type typeToken = new TypeToken<List<Exchange>>() {
    }.getType();
    private static final List<Exchange> exchanges = new ArrayList<>();

    static {
        updateExchangeList();
    }

    public PrivatbankUtils() {
        super(COMMAND_NAME, COMMAND_RESULT_TEXT);
    }

    public static void updateExchangeList() {
        List<Exchange> exchangeList = BankHttpUtils.getExchangeList(URL, typeToken);
        synchronized (exchanges){
            exchanges.clear();
            exchanges.addAll(exchangeList);
        }
    }

    @Override
    public List<Exchange> getExchangeList() {
        List<Exchange> exchangeList;
        synchronized (exchanges){
            exchangeList = List.copyOf(exchanges);
        }
        return exchangeList;
    }

}
