package banksUtils.monobank;

import banksUtils.BankHttpUtils;
import com.google.gson.reflect.TypeToken;
import model.Bank;
import model.Exchange;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class MonobankUtils extends Bank {
    private final static String URL = "https://api.monobank.ua/bank/currency";
    private final static Type typeToken = new TypeToken<List<MonoExchange>>() {
    }.getType();
    private static List<Exchange> exchanges;

    static {
        updateExchangeList();
    }

    public static void updateExchangeList() {
        List<MonoExchange> monoExchanges = BankHttpUtils.getExchangeList(URL, typeToken);
        exchanges = monoExchanges.stream().map(MonoExchange::getExchange).collect(Collectors.toList());
    }

    @Override
    public List<Exchange> getExchangeList() {
        return List.copyOf(exchanges);
    }

    @Override
    public String getButtonCallbackData() {
        return "/setMonobank";
    }


    public String toString() {
        return "Monobank";
    }

}
