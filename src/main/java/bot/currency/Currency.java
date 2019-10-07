package bot.currency;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Currency {

    public static double getCurrency(String id) throws IOException {
        if (id.equals("UAH")) {
            return 1;
        } else {
            Model model = new Model();
            StringBuilder builder = new StringBuilder();
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyyMMdd");
            URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=" +
                    id + "&date=" + formatForDateNow.format(dateNow) + "&json");
            Scanner in = new Scanner((InputStream) url.getContent());
            while (in.hasNext()) {
                builder.append(in.nextLine());
            }
            builder.deleteCharAt(0).deleteCharAt(builder.length() - 1);
            JSONObject object = new JSONObject(builder.toString());
            model.setRate(object.getDouble("rate"));
            return model.getRate();
        }
    }
}
