package nl.hybrit.lightsaber.shop.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Formatter {

    public static String getFormattedPrice(double price){
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        return nf.format(price);
    }
}
