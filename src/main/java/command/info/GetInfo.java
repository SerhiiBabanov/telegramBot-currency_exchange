package command.info;

import command.start.Start;
import model.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GetInfo extends SendCommand {
    public static final String COMMAND_NAME = "/getInfo";
    protected static final String BUTTON_TEXT = "Отримати інфо";
    protected static final String COMMAND_RESULT_TEXT = "EmptyText";
    protected static final String PARENT_COMMAND = Start.COMMAND_NAME;
    public GetInfo() {
        super(COMMAND_NAME, BUTTON_TEXT, COMMAND_RESULT_TEXT, PARENT_COMMAND);
    }
    private String getExchangeAfterRoundResults(Exchange exchange, ChatSetting chatSetting) {
        BigDecimal sale = BigDecimal.valueOf(exchange.getSale()).setScale(chatSetting.getRoundDigit(), RoundingMode.HALF_UP);
        BigDecimal buy = BigDecimal.valueOf(exchange.getBuy()).setScale(chatSetting.getRoundDigit(), RoundingMode.HALF_UP);
        return exchange.ccy + '\\' + exchange.base_ccy + System.lineSeparator() +
                "Покупка:" + buy + System.lineSeparator() +
                "Продажа:" + sale + System.lineSeparator();
    }
    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatSetting.getChatId());
        sendMessage.setText("Результати не знайдені");
        StringBuilder result = new StringBuilder();

        List<Bank> banks = TelegramBot.getBanks();
        List<Exchange> exchangeList = null;
        for (Bank bank: banks
             ) {
            if (bank.getCommandName().equals(chatSetting.getBank().getCommandName())){
                exchangeList = bank.getExchangeList();
                result.append("Курс в ").append(bank.getName()).append(System.lineSeparator());
                break;
            }

        }

        if (Objects.nonNull(exchangeList)) {
            for (Currency v : chatSetting.getCurrencies()
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

    @Override
    public List<List<InlineKeyboardButton>> getKeyboard(ChatSetting chatSetting) {
        return null;
    }

    @Override
    public InlineKeyboardButton getBackButton() {
        return null;
    }


}
