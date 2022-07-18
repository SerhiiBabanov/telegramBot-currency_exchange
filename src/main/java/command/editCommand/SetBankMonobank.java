package command.editCommand;

import bank.monobank.Monobank;
import com.vdurmont.emoji.EmojiParser;
import model.ChatSetting;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class SetBankMonobank extends EditCommand{
    public SetBankMonobank() {
        commandName = "/setMonobank";
        buttonText = "Set Bank Monobank";
    }

    @Override
    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatSetting.getChatId());
        editMessageText.setMessageId(messageId);
        InlineKeyboardButton monobank = new SetBankMonobank().getButton();
        InlineKeyboardButton privatbank = new SetBankPrivatbank().getButton();
        InlineKeyboardButton nbu = new SetBankNBU().getButton();
        monobank.setText(EmojiParser.parseToUnicode(":white_check_mark:" + monobank.getText()));
        chatSetting.setBank(new Monobank());
        repository.add(chatSetting.getChatId(), chatSetting);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine0 = new ArrayList<>();
        rowInLine0.add(monobank);
        rowsInLine.add(rowInLine0);
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        rowInLine1.add(privatbank);
        rowsInLine.add(rowInLine1);
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        rowInLine2.add(nbu);
        rowsInLine.add(rowInLine2);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        editMessageText.setText("Change setting");
        return editMessageText;
    }
}
