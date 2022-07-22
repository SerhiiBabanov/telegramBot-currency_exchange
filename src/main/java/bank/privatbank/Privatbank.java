package bank.privatbank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Bank;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Privatbank extends Bank {
    @Override
    public List<Exchange> getExchangeList() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11"))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Gson().fromJson(response.body(), new TypeToken<List<Exchange>>() {}.getType());
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
