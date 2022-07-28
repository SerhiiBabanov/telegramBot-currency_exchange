package command.info;

import banksUtils.monobank.MonobankUtils;
import banksUtils.nbu.NBUUtils;
import banksUtils.privatbank.PrivatbankUtils;
import model.SendCommand;
import model.ChatSetting;
import model.Currency;
import model.Exchange;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GetInfo extends SendCommand {
    public GetInfo() {
        commandName = "/getInfo";
        buttonText = "Отримати інфо";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatSetting.getChatId());
        sendMessage.setText("Результати не знайдені");
        StringBuilder result = new StringBuilder();

        List<Exchange> exchangeList = null;
        if (chatSetting.getBank().toString().equals("Monobank")) {
            result.append("Курс в Монобанк").append(System.lineSeparator());
            exchangeList = new MonobankUtils().getExchangeList();
        }
        if (chatSetting.getBank().toString().equals("NBU")) {
            result.append("Курс в НБУ").append(System.lineSeparator());
            exchangeList = new NBUUtils().getExchangeList();
        }
        if (chatSetting.getBank().toString().equals("/privatbank")) {
            result.append("Курс в ПриватБанк").append(System.lineSeparator());
            exchangeList = new PrivatbankUtils().getExchangeList();
        }

        if (Objects.nonNull(exchangeList)) {
            for (Currency v : chatSetting.getValutes()
            ) {
                Optional<Exchange> exchange = exchangeList.stream()
                        .filter(ex -> ex.ccy.equalsIgnoreCase(v.name()))
                        .findFirst();
                exchange.ifPresent(ex -> result.append(getExchangeAfterRoundResults(ex, chatSetting))
                        .append(System.lineSeparator()));
            }

            sendMessage.setText(result.toString());
        }

        return sendMessage;
    }

    String getExchangeAfterRoundResults(Exchange exchange, ChatSetting chatSetting) {
        BigDecimal sale = BigDecimal.valueOf(exchange.getSale()).setScale(chatSetting.getRoundDigit(), RoundingMode.HALF_UP);
        BigDecimal buy = BigDecimal.valueOf(exchange.getBuy()).setScale(chatSetting.getRoundDigit(), RoundingMode.HALF_UP);
        return exchange.ccy + '\\' + exchange.base_ccy + System.lineSeparator() +
                "Покупка:" + buy +
                "Продажа:" + sale + System.lineSeparator();
    }
}
