package bot;

import bot.currency.Currency;
import bot.utils.*;
import bot.userDatabase.UserDataBase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendResponse(message.getChatId().toString(), "Здравствуйте, напишите ваш запрос в формате \"Сумма + исходная валюта + конечная валюта\" " +
                            "или просто введите сумму для конвертации.");
                    new UserDataBase().addUserToBase(message.getChatId().toString());
                    break;
                case "/test":
                    new MassMailing().start();
                    break;
                case "\u2753" + " Помощь":
                    sendResponse(message.getChatId().toString(), "\uD83D\uDD34 Напишите ваш запрос в формате \"Сумма + исходная валюта + конечная валюта\" " +
                            "или просто введите сумму для конвертации.\n\n" +
                            "\uD83D\uDD34 Бот видит только целочисельные значения суммы.");
                    break;
                case "\uD83E\uDD16" + " О боте":
                    sendResponse(message.getChatId().toString(), "\uD83D\uDD34 Этот бот предназначен для конвертации валюты по курсу НБУ на текущую дату.\n\n" +
                            "\uD83D\uDD34 Поддерживаемые языки ввода: English, Русский, Українська.\n\n" +
                            "\uD83D\uDD34 Поддерживаемая валюта: доллар, евро, гривна, рубли, фунты.\n\n" +
                            "\uD83D\uDD34 Разработчик: @nickmage.");
                    break;
                default:
                    createResponse(message.getChatId().toString(), message.getText());
            }
        } else if (update.hasCallbackQuery()) {
            editResponse(update);
        }
    }

    private void createResponse(String chatId, String message) {
        double quantity = 0;
        int i;
        for (i = 0; i < message.length(); i++) {
            if (message.charAt(i) >= '0' && message.charAt(i) <= '9') {
                quantity *= 10;
                quantity += message.charAt(i) - '0';
            } else {
                break;
            }
        }
        if (quantity == 0) {
            sendResponse(chatId, "Отсутствует число для конвертации или его значение должно быть 1 или больше.");
            return;
        }
        String from;
        String to;
        if (i == message.length()) {                                                    //number only
            //sendResponse(chatId, "Выберите валюты для конвертации ниже.");
            showInlineKeyboard(chatId, (int) quantity, "Выберите валюты для конвертации ниже.");
        } else {
            from = StringTransformer.getStringFrom(message.substring(i));
            if ((i + from.length() + 1) > message.length()) {
                to = "";
            } else {
                to = StringTransformer.getStringTo(message.substring(i + from.length() + 1));
            }
            from = Dictionary.getId(from);
            to = Dictionary.getId(to);
            if (from.equals("") || to.equals("")) {                                         // любой из них неправльный
                //sendResponse(chatId, "Не могу опознать валюту. Выберите обе валюты для конвертации ниже.");
                showInlineKeyboard(chatId, (int) quantity, "Не могу опознать валюту. Выберите обе валюты для конвертации ниже.");
            } else {
                sendResponse(chatId, calculateCurrency(quantity, from, to));
            }
        }
    }

    private String calculateCurrency(double quantity, String from, String to) {
        double coefficientFrom = 1;
        double coefficientTo = 1;
        try {
            coefficientFrom = Currency.getCurrency(from);
            coefficientTo = Currency.getCurrency(to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String formattedValue = new DecimalFormat("###,###.##")
                .format(quantity * coefficientFrom / coefficientTo);
        return (int) quantity + " " + from + " " + EmojiFlag.getFlag(from) + " равно " + formattedValue +
                " " + to + " " + EmojiFlag.getFlag(to);
    }

    public void sendResponse(String chatId, String text) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        Keyboard.setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void editResponse(Update update) {
        String callData = update.getCallbackQuery().getData();
        if (callData.startsWith("!")) {
            finalResponse(update, callData);
        } else {
            AvailableCurrency availableCurrency = new AvailableCurrency();
            for (int i = 0; i < availableCurrency.size(); i++) {
                if (callData.startsWith(availableCurrency.getAvailableCurrency(i)))
                    showChangedInlineKeyboard(update, availableCurrency.getAvailableCurrency(i));
            }
        }
    }

    private void showInlineKeyboard(String chatId, int quantity, String text) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineRowList = new InlineButtonList().getList(quantity);
        inlineKeyboard.setKeyboard(inlineRowList);
        sendMessage.setReplyMarkup(inlineKeyboard);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void showChangedInlineKeyboard(Update update, String selected) {
        String response = "Конвертировать " + update.getCallbackQuery().getData().substring(3) + " "
                + selected + " " + EmojiFlag.getFlag(selected) + " в";
        EditMessageText editedMessage = new EditMessageText()
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setText(response);
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineRowList = new InlineButtonList().getSecondList(selected, update.getCallbackQuery().getData());
        inlineKeyboard.setKeyboard(inlineRowList);
        editedMessage.setReplyMarkup(inlineKeyboard);
        try {
            execute(editedMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void finalResponse(Update update, String callData) {
        double quantity = Double.parseDouble(callData.substring(4, callData.length() - 3));
        String from = callData.substring(1, 4);
        String to = callData.substring(callData.length() - 3);
        String response = calculateCurrency(quantity, from, to);
        EditMessageText editedMessage = new EditMessageText()
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setText(response);
        try {
            execute(editedMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

}
