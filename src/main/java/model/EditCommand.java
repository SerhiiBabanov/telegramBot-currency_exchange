package model;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import repository.Repository;

public abstract class EditCommand extends Command {
    private final String parentCommand;

    public String getParentCommand() {
        return parentCommand;
    }

    protected EditCommand(String commandName, String buttonText, String commandResultText, String parentCommand) {
        super(commandName, buttonText, commandResultText);
        this.parentCommand = parentCommand;
    }

    public EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository){
        setSetting(chatSetting, repository);
        return EditMessageText.builder()
                .text(commandResultText)
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(getParentCommandObj(parentCommand).getKeyboard(chatSetting))
                        .build())
                .chatId(chatSetting.getChatId())
                .messageId(messageId)
                .build();
    }
    public abstract void setSetting(ChatSetting chatSetting, Repository repository);
    private SendCommand getParentCommandObj(String parentCommand){
        return TelegramBot.getSendCommands().stream()
                .filter(sendCommand -> sendCommand.getCommandName().equals(parentCommand))
                .findFirst()
                .orElse(null);

    }
}
