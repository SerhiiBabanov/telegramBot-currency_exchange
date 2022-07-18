package command.editCommand;

import com.vdurmont.emoji.EmojiParser;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class RoundDigitSettingTree extends EditCommand{
    public RoundDigitSettingTree() {
        commandName = "/roundDigitSettingTree";
        buttonText = "roundDigitSettingTree";
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatSetting.getChatId());
        editMessageText.setMessageId(messageId);
        InlineKeyboardButton two_digit = new RoundDigitSettingTwo().getButton();
        InlineKeyboardButton tree_digit = new RoundDigitSettingTree().getButton();
        tree_digit.setText(EmojiParser.parseToUnicode(":white_check_mark:" + tree_digit.getText()));
        chatSetting.setRoundDigit(3);
        repository.add(chatSetting.getChatId(), chatSetting);
        InlineKeyboardButton four_digit = new RoundDigitSettingFour().getButton();
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
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        editMessageText.setText("Change setting");
        return editMessageText;
    }
}
