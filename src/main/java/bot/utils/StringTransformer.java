package bot.utils;

public class StringTransformer {

    public static String getStringFrom(String base) {
        if (base.length() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < base.length()) {
            if (base.charAt(i) == ' ' || (base.charAt(i) >= '0' && base.charAt(i) <= '9') || base.charAt(i) == '.'
                    || base.charAt(i) == '(' || base.charAt(i) == ')' || base.charAt(i) == ',') {
                i++;
            } else {
                break;
            }
        }
        for (int j = i; j < base.length(); j++) {
            if (base.charAt(j) != ' ' ^ (base.charAt(j) >= '0' && base.charAt(j) <= '9')) {
                builder.append(base.charAt(j));
            } else {
                break;
            }
        }
        return builder.toString();
    }

    public static String getStringTo(String base) {
        if (base.length() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int i = base.length() - 1;
        while (i >= 0) {
            if (base.charAt(i) == ' ' || (base.charAt(i) >= '0' && base.charAt(i) <= '9') || base.charAt(i) == '.'
                    || base.charAt(i) == '(' || base.charAt(i) == ')' || base.charAt(i) == ',') {
                i--;
            } else {
                break;
            }
        }
        for (int j = i; j >= 0; j--) {
            if (base.charAt(j) != ' ' ^ (base.charAt(j) >= '0' && base.charAt(j) <= '9')) {
                builder.insert(0,base.charAt(j));
            } else {
                break;
            }
        }
        return builder.toString();
    }
}
