package model;

import banksUtils.privatbank.PrivatbankUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ChatSetting {
    private long chatId;
    private int roundDigit;
    private Bank bank;
    private int reminderTime;
    private List<Currency> currencies;
    private Locale locale;

    public ChatSetting(long chatId) {
        this.chatId = chatId;
    }

    public ChatSetting(long chatId, int roundDigit, Bank bank, int reminderTime, List<Currency> currencies, Locale locale) {
        this.chatId = chatId;
        this.roundDigit = roundDigit;
        this.bank = bank;
        this.reminderTime = reminderTime;
        this.currencies = currencies;
        this.locale = locale;
    }

    public static ChatSetting getDefault(long chatId) {
        ChatSetting defaultSetting = new ChatSetting(chatId);
        defaultSetting.setBank(new PrivatbankUtils());
        defaultSetting.setRoundDigit(2);
        List<Currency> currencyList = new ArrayList<>();

        currencyList.add(Currency.USD);
        defaultSetting.setCurrencies(currencyList);
        defaultSetting.setReminderTime(13);
        defaultSetting.setLocale(new Locale("ua"));
        return defaultSetting;
    }

    public long getChatId() {
        return chatId;
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

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
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
