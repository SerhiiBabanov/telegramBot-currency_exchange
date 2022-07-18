package repository;

import model.ChatSetting;

import java.util.*;

public class InMemoryMapRepository implements Repository{
    private final Map<Long,ChatSetting> chatSettings;

    public InMemoryMapRepository() {
        this.chatSettings = new HashMap<>();
    }

    @Override
    public boolean contains(long chatId) {
        return chatSettings.containsKey(chatId);
    }

    @Override
    public void add(long chatId, ChatSetting chatSetting) {
        chatSettings.put(chatId, chatSetting);
    }

    @Override
    public void delete(long chatId) {
        chatSettings.remove(chatId);
    }

    @Override
    public ChatSetting getSetting(long chatId) {
        return chatSettings.get(chatId);
    }
}
