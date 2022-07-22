package model;

import bank.privatbank.Privatbank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatSetting {
    long chatId;
    int roundDigit;
    Bank bank;
    int reminderTime;
    List<Valute> valutes;

    public ChatSetting(long chatId) {
        this.chatId = chatId;
    }

    public ChatSetting(long chatId, int roundDigit, Bank bank, int reminderTime, List<Valute> valutes) {
        this.chatId = chatId;
        this.roundDigit = roundDigit;
        this.bank = bank;
        this.reminderTime = reminderTime;
        this.valutes = valutes;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public int getRoundDigit() {
        return roundDigit;
    }

    public void setRoundDigit(int roundDigit) {
        this.roundDigit = roundDigit;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getReminderTime() {
        return reminderTime;
    }
    public static ChatSetting getDefault(long chatId){
        ChatSetting defaultSetting = new ChatSetting(chatId);
        defaultSetting.setBank(new Privatbank());
        defaultSetting.setRoundDigit(2);
        List<Valute> valuteList = new ArrayList<>();

        valuteList.add(Valute.USD);
        defaultSetting.setValutes(valuteList);
        return defaultSetting;
    }

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }

    public List<Valute> getValutes() {
        return valutes;
    }

    public void setValutes(List<Valute> valutes) {
        this.valutes = valutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatSetting that = (ChatSetting) o;
        return chatId == that.chatId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }
}
