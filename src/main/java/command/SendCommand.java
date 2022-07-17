package command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class SendCommand extends Command{
    public abstract SendMessage execute(long chatId);
}
