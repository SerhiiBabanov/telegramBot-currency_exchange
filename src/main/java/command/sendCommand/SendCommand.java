package command.sendCommand;

import command.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public abstract class SendCommand extends Command {
    public abstract SendMessage execute(long chatId);
}
