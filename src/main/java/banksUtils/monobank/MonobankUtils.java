package banksUtils.monobank;

import banksUtils.BankHttpUtils;
import banksUtils.nbu.NBUExchange;
import model.Exchange;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Bank;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class MonobankUtils extends Bank {
    private final static String URL = "https://api.monobank.ua/bank/currency";
    private final static Type typeToken = new TypeToken<List<MonoExchange>>() {}.getType();
    private static List<Exchange> exchanges;

    static {
        updateExchangeList();
    }

    public static void updateExchangeList(){
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
