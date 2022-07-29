package model;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import repository.Repository;

public abstract class EditCommand extends Command {
    public String commandResultText;
    public final SendCommand parentCommand;

    protected EditCommand(SendCommand parentCommand) {
        this.parentCommand = parentCommand;
    }

    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository){
        setSetting(chatSetting, repository);
        return EditMessageText.builder()
                .text(commandResultText)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(parentCommand.getKeyboard(chatSetting)).build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();
    }
    public abstract void setSetting(ChatSetting chatSetting, Repository repository);
}
