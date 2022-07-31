package repository;

import model.ChatSetting;

import java.util.List;

public interface Repository {
    boolean contains(long chatId);

    void add(long chatId, ChatSetting chatSetting);

    void delete(long chatId);

    ChatSetting getSetting(long chatId);

    List<ChatSetting> getListOfSettings();
}
