package banksUtils.monobank;

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

public class MonobankUtils extends Bank {


    @Override
    public List<Exchange> getExchangeList() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.monobank.ua/bank/currency"))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<MonoExchange> nbuExchanges = new Gson().fromJson(response.body(), new TypeToken<List<MonoExchange>>() {}.getType());
        return nbuExchanges.stream().map(MonoExchange::getExchange).collect(Collectors.toList());
    }

    @Override
    public String getButtonCallbackData() {
        return "/setMonobank";
    }


    public String toString() {
        return "Monobank";
    }

}
