package repository;

import model.ChatSetting;

import java.util.*;

public class InMemoryMapRepository implements Repository{
    private final List<ChatSetting> chatSettings;

    public InMemoryMapRepository() {
        this.chatSettings = new ArrayList<>();
    }

    @Override
    public boolean contains(long chatId) {
        return chatSettings.stream().anyMatch(chatSetting -> chatSetting.getChatId() == chatId);
    }

    @Override
    public void add(long chatId, ChatSetting chatSetting) {
        for (int i = 0; i < chatSettings.size(); i++) {
            if (chatSettings.get(i).getChatId() == chatId){
                chatSettings.set(i, chatSetting);
                return;
            }
        }
        chatSettings.add(chatSetting);
    }

    @Override
    public void delete(long chatId) {
        for (int i = 0; i < chatSettings.size(); i++) {
            if (chatSettings.get(i).getChatId() == chatId){
                chatSettings.remove(i);
                return;
            }
        }
    }

    @Override
    public ChatSetting getSetting(long chatId) {
        for (ChatSetting chatSetting : chatSettings) {
            if (chatSetting.getChatId() == chatId) {
                return chatSetting;
            }
        }
        return null;
    }

    @Override
    public List<ChatSetting> getListOfSettings() {
        return chatSettings;
    }
}
