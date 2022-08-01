package banksUtils.nbu;

import banksUtils.BankHttpUtils;
import com.google.gson.reflect.TypeToken;
import model.Bank;
import model.Exchange;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NBUUtils extends Bank {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    public static final String COMMAND_NAME = "/setNBU";
    protected static final String BUTTON_TEXT = "НБУ";
    protected static final String COMMAND_RESULT_TEXT = "Курс в НБУ";

    private static final Type typeToken = new TypeToken<List<NBUExchange>>() {
    }.getType();
    private static final List<Exchange> exchanges = new ArrayList<>();

    static {
        updateExchangeList();
    }

    public NBUUtils() {
        super(COMMAND_NAME, COMMAND_RESULT_TEXT);
    }

    public static void updateExchangeList() {
        List<NBUExchange> nbuExchanges = BankHttpUtils.getExchangeList(URL, typeToken);
        List<Exchange> exchangeList = nbuExchanges.stream().map(NBUExchange::getExchange).toList();
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
