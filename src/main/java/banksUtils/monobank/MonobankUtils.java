package banksUtils.monobank;

import banksUtils.BankHttpUtils;
import com.google.gson.reflect.TypeToken;
import model.Bank;
import model.Exchange;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MonobankUtils extends Bank {
    private final static String URL = "https://api.monobank.ua/bank/currency";
    public static final String COMMAND_NAME = "/setMonobank";
    protected static final String NAME = "Монобанк";

    private final static Type typeToken = new TypeToken<List<MonoExchange>>() {
    }.getType();
    private static final List<Exchange> exchanges = new ArrayList<>();

    static {
        updateExchangeList();
    }

    public MonobankUtils() {
        super(COMMAND_NAME, NAME);
    }

    public static void updateExchangeList() {
        List<MonoExchange> monoExchanges = BankHttpUtils.getExchangeList(URL, typeToken);
        List<Exchange> exchangeList = monoExchanges.stream().map(MonoExchange::getExchange).toList();
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
