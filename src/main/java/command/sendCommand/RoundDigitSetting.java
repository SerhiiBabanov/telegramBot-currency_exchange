package command.sendCommand;


import com.vdurmont.emoji.EmojiParser;
import command.editCommand.RoundDigitSettingFour;
import command.editCommand.RoundDigitSettingTree;
import command.editCommand.RoundDigitSettingTwo;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RoundDigitSetting extends SendCommand{
    public RoundDigitSetting() {
        commandName = "/roundDigitSetting";
        buttonText = "RoundDigitSetting";
    }

    @Override
    public SendMessage execute(ChatSetting chatSetting) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatSetting.getChatId());
        sendMessage.setText(commandName);
        InlineKeyboardButton two_digit = new RoundDigitSettingTwo().getButton();
        InlineKeyboardButton tree_digit = new RoundDigitSettingTree().getButton();
        InlineKeyboardButton four_digit = new RoundDigitSettingFour().getButton();
        if (chatSetting.getRoundDigit() == 2){
            two_digit.setText(EmojiParser.parseToUnicode(":white_check_mark:" + two_digit.getText()));
        }
        if (chatSetting.getRoundDigit() == 3){
            tree_digit.setText(EmojiParser.parseToUnicode(":white_check_mark:" + tree_digit.getText()));
        }
        if (chatSetting.getRoundDigit() == 4){
            four_digit.setText(EmojiParser.parseToUnicode(":white_check_mark:" + four_digit.getText()));
        }
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine0 = new ArrayList<>();
        rowInLine0.add(two_digit);
        rowsInLine.add(rowInLine0);
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        rowInLine1.add(tree_digit);
        rowsInLine.add(rowInLine1);
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        rowInLine2.add(four_digit);
        rowsInLine.add(rowInLine2);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
