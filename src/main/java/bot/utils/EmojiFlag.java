package bot.utils;

public class EmojiFlag {
    public static String getFlag(String str) {
        switch (str) {
            case "UAH":
                return "\uD83C\uDDFA\uD83C\uDDE6";
            case "USD":
                return "\uD83C\uDDFA\uD83C\uDDF8";
            case "EUR":
                return "\uD83C\uDDEA\uD83C\uDDFA";
            case "RUB":
                return "\uD83C\uDDF7\uD83C\uDDFA";
            case "GBP":
                return "\uD83C\uDDEC\uD83C\uDDE7";
        }
        return "";
    }
}
