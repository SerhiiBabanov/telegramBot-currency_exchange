package command.editCommand;

import command.Command;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public abstract class EditCommand extends Command {
    public abstract EditMessageText execute(long chatId);
}
