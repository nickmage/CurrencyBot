package bot.utils;

public class AvailableCurrency {
    private String[] availableCurrency = {"USD", "EUR", "UAH", "RUB", "GBP"};

    public int size(){
        return availableCurrency.length;
    }

    public String getAvailableCurrency(int index) {
        return availableCurrency[index];
    }
}
