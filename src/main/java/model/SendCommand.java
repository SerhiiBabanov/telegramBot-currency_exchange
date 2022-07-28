package model;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class SendCommand extends Command {
    public abstract SendMessage execute(ChatSetting chatSetting);
}
