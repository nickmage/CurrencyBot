package bot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineButtonList {
    private AvailableCurrency availableCurrency = new AvailableCurrency();

    public List<List<InlineKeyboardButton>> getList(int quantity) {
        List<List<InlineKeyboardButton>> inLineRowList = new ArrayList<>();

        List<InlineKeyboardButton> inLineRow1 = new ArrayList<>();
        inLineRow1.add(new InlineKeyboardButton().setText("USD " + EmojiFlag.getFlag("USD"))
                .setCallbackData("USD" + quantity));
        inLineRow1.add(new InlineKeyboardButton().setText("EUR" + EmojiFlag.getFlag("EUR"))
                .setCallbackData("EUR" + quantity));
        inLineRowList.add(inLineRow1);

        List<InlineKeyboardButton> inLineRow2 = new ArrayList<>();
        inLineRow2.add(new InlineKeyboardButton().setText("UAH" + EmojiFlag.getFlag("UAH"))
                .setCallbackData("UAH" + quantity));
        inLineRow2.add(new InlineKeyboardButton().setText("RUB" + EmojiFlag.getFlag("RUB"))
                .setCallbackData("RUB" + quantity));
        inLineRow2.add(new InlineKeyboardButton().setText("GBP" + EmojiFlag.getFlag("GBP"))
                .setCallbackData("GBP" + quantity));
        inLineRowList.add(inLineRow2);

        return inLineRowList;
    }

    public List<List<InlineKeyboardButton>> getSecondList(String selected, String callData) {
        List<List<InlineKeyboardButton>> inLineRowList = new ArrayList<>();

        List<InlineKeyboardButton> inLineRow1 = new ArrayList<>();
        int i = 0;
        while (inLineRow1.size() < 2) {
            if (selected.startsWith(availableCurrency.getAvailableCurrency(i))) {
                i++;
            } else {
                inLineRow1.add(new InlineKeyboardButton().setText(availableCurrency.getAvailableCurrency(i)
                        + EmojiFlag.getFlag(availableCurrency.getAvailableCurrency(i)))
                        .setCallbackData("!"+callData + availableCurrency.getAvailableCurrency(i)));
                i++;
            }
        }
        inLineRowList.add(inLineRow1);

        List<InlineKeyboardButton> inLineRow2 = new ArrayList<>();
        while (inLineRow2.size() < 2) {
            if (selected.startsWith(availableCurrency.getAvailableCurrency(i))) {
                i++;
            } else {
                inLineRow2.add(new InlineKeyboardButton().setText(availableCurrency.getAvailableCurrency(i)
                        + EmojiFlag.getFlag(availableCurrency.getAvailableCurrency(i)))
                        .setCallbackData("!"+callData + availableCurrency.getAvailableCurrency(i)));
                i++;
            }
        }
        inLineRowList.add(inLineRow2);

        return inLineRowList;
    }

}