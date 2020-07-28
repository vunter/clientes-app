package net.ddns.salp.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter {

    public static BigDecimal converter(String value) {
        if (value.contains(",")) {
            value = value.replace(".", "").replace(",", ".");
        }
        return new BigDecimal(value);
    }
}
