package banksUtils.nbu;

import model.Exchange;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Bank;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class NBUUtils extends Bank {


    @Override
    public List<Exchange> getExchangeList() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json"))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<NBUExchange> nbuExchanges = new Gson().fromJson(response.body(), new TypeToken<List<NBUExchange>>() {}.getType());
        return nbuExchanges.stream().map(NBUExchange::getExchange).collect(Collectors.toList());
    }

    @Override
    public String getButtonCallbackData() {
        return "/setNBU";
    }


    public String toString() {
        return "NBU";
    }
}
