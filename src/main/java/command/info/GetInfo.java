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
        buttonText = "GetInfo";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatSetting.getChatId());
        sendMessage.setText("Results not found");
        StringBuilder result = new StringBuilder();

        List<Exchange> exchangeList = null;
        if (chatSetting.getBank().toString().equals("Monobank")) {
            result.append("Monobank");
            exchangeList = new MonobankUtils().getExchangeList();
        }
        if (chatSetting.getBank().toString().equals("NBU")) {
            result.append("NBU");
            exchangeList = new NBUUtils().getExchangeList();
        }
        if (chatSetting.getBank().toString().equals("/privatbank")) {
            result.append("Privatbank");
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
        return "Exchange{" +
                "ccy='" + exchange.getCcy() + '\'' +
                ", base_ccy='" + exchange.getBase_ccy() + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
