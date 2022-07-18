package command.sendCommand;

import bank.privatbank.Privatbank;
import com.vdurmont.emoji.EmojiParser;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class GetInfo extends SendCommand{
    public GetInfo() {
        commandName = "/getInfo";
        buttonText = "GetInfo";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatSetting.getChatId());
        StringBuilder result = new StringBuilder();
        if (chatSetting.getBank().toString().equals("Monobank")){

        }
        if (chatSetting.getBank().toString().equals("NBU")){

        }
        if (chatSetting.getBank().toString().equals("Privatbank")){
            result.append("Privatbank");
            Privatbank privatbank = new Privatbank();
            chatSetting.getValutes().stream().forEach(valute -> {
                result.append(privatbank.getValuteExchangeBay(valute));
                result.append("//");
                result.append(privatbank.getValuteExchangeSell(valute));
            });
        }
        sendMessage.setText(result.toString());

        return sendMessage;
    }
}
