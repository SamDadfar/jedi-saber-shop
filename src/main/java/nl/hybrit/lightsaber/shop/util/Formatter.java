package nl.hybrit.lightsaber.shop.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatter {

    final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getFormattedPrice(double price) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        return nf.format(price);
    }

    public static LocalDate getDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }
}
