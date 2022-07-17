package command;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public abstract class EditCommand extends Command{
    public abstract EditMessageText execute(long chatId);
}
