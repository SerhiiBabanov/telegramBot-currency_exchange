import model.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import repository.InMemoryListRepository;
import repository.Repository;

public class AppLauncher {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        Repository chatSettings = new InMemoryListRepository();

        try {
            telegramBotsApi.registerBot(new TelegramBot(chatSettings));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}