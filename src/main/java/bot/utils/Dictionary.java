package bot.utils;

import java.util.Arrays;

public class Dictionary {

    public static String getId(String str) {
        String[] USDPattern = {
                "$", "dolar", "dolars", "doll", "dollar", "dollars", "in$", "indolar", "indolars",
                "indollar", "indollars", "inusd", "to$", "todolar", "todolars", "todollar", "todollars",
                "tousd", "usd", "вдол", "вдоларах", "вдолл", "вдолларах", "длр", "додоларів", "дол", "долар",
                "долара", "доларами", "доларах", "долари", "доларов", "долары", "доларів", "долл", "доллар",
                "доллара", "долларами", "долларах", "долларов", "доллары", "долр", "кдоларам", "кдолару", "кдолларам",
                "кдоллару", "удоларах"
        };

        String[] UAHPattern = {
                "grn", "grns", "gryvnia", "gryvnias", "hryvnia", "hryvnias", "ingrn", "ingrns", "ingryvnia",
                "ingryvnias", "inhryvnia", "inhryvnias", "inuah", "inuahs", "in₴", "togrn", "togrns",
                "togryvnia", "togryvnias", "tohryvnia", "tohryvnias", "touah", "touahs", "to₴", "uah", "uahs",
                "вгривнах", "вгривнях", "вгрн", "в₴", "гривен", "гривень", "гривна", "гривнах", "гривни", "гривне",
                "гривну", "гривны", "гривня", "гривнях", "гривні", "грн", "догривен", "догривень", "до₴", "кгривнам",
                "кгривням", "кгрн", "к₴", "угривнах", "угривнях", "угрн", "₴"
        };

        String[] EURPattern = {
                "ebpo", "eur", "euro", "euros", "eurs", "ineur", "ineuro", "ineuros", "in€", "toeur",
                "toeuro", "toeuros", "toeurs", "to€", "вевр", "вевро", "вєвро", "доєвро", "евр", "евро",
                "кевро", "уєвро", "євр", "євро", "€",
        };

        String[] GBPPattern = {
                "funt", "funts", "gbp", "gbpounds", "infunt", "infunts", "ingbp", "inpound", "inpounds",
                "in£", "pnd", "pnds", "pound", "pounds", "tofunt", "tofunts", "togbp", "topound", "topounds",
                "to£", "£", "вфунт", "вфунтах", "гбп", "дофунтів", "кфунтам", "кфунту", "уфунтах", "фунт", "фунта",
                "фунтами", "фунтах", "фунти", "фунтов", "фунты", "фунтів"
        };

        String[] RUBPattern = {
                "inrub", "inruble", "inrubles", "inrubs", "inrur", "inrurs", "in₽", "rub", "ruble", "rubles",
                "rubs", "rur", "rurs", "torub", "toruble", "torubles", "torubs", "torur", "torurs", "to₽",
                "вруб", "врублях", "врур", "в₽", "дорублів", "до₽", "крублям", "к₽", "руб", "рублей", "рубли",
                "рубль", "рубля", "рублях", "рублі", "рур", "урублях", "₽"
        };

        if (Arrays.asList(USDPattern).contains(str)) {
            return "USD";
        } else if (Arrays.asList(EURPattern).contains(str)) {
            return "EUR";
        } else if (Arrays.asList(UAHPattern).contains(str)) {
            return "UAH";
        } else if (Arrays.asList(RUBPattern).contains(str)) {
            return "RUB";
        } else if (Arrays.asList(GBPPattern).contains(str)) {
            return "GBP";
        }
        return "";
    }
}
