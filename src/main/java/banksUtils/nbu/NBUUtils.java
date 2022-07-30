package banksUtils.nbu;

import banksUtils.BankHttpUtils;
import banksUtils.monobank.MonoExchange;
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

public class NBUUtils extends Bank {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final Type typeToken = new TypeToken<List<NBUExchange>>() {}.getType();
    private static List<Exchange> exchanges;

    static {
        updateExchangeList();
    }

    public static void updateExchangeList(){
        List<NBUExchange> nbuExchanges = BankHttpUtils.getExchangeList(URL, typeToken);
        exchanges = nbuExchanges.stream().map(NBUExchange::getExchange).collect(Collectors.toList());
    }
    @Override
    public List<Exchange> getExchangeList() {
        return List.copyOf(exchanges);
    }

    @Override
    public String getButtonCallbackData() {
        return "/setNBU";
    }


    public String toString() {
        return "NBU";
    }
}
