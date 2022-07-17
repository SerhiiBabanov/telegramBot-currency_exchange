package repository;

import model.ChatSetting;

import java.util.Optional;

public interface Repository {
    boolean contains(long chatId);
    void add(long chatId, ChatSetting chatSetting);
    void delete(long chatId);
    Optional<ChatSetting> getSetting(long chatId);
}
