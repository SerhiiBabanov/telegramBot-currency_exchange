package model;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import repository.Repository;

public abstract class EditCommand extends Command {
    public abstract EditMessageText execute(ChatSetting chatSetting, int messageId, Repository repository);
}
