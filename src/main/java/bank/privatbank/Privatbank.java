package bank.privatbank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Bank;
import model.Valute;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public class Privatbank extends Bank {
    @Override
    public double getValuteExchangeBay(Valute valute) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=4"))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Exchange> exchangeList = new Gson().fromJson(response.body(), new TypeToken<List<Exchange>>() {}.getType());
        Optional<Double> result = exchangeList.stream()
                .filter(exchange -> exchange.ccy.equalsIgnoreCase(valute.name()))
                .map(Exchange::getBuy)
                .findFirst();

        return result.orElse(0.0);
    }

    @Override
    public double getValuteExchangeSell(Valute valute) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=4"))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Exchange> exchangeList = new Gson().fromJson(response.body(), new TypeToken<List<Exchange>>() {}.getType());
        Optional<Double> result = exchangeList.stream()
                .filter(exchange -> exchange.ccy.equalsIgnoreCase(valute.name()))
                .map(Exchange::getSale)
                .findFirst();
        return result.orElse(0.0);
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
