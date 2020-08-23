package net.ddns.salp.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class BigDecimalConverter {

    public BigDecimal converter(String value) {
        if (value.contains(",")) {
            value = value.replace(".", "").replace(",", ".");
        }
        return new BigDecimal(value);
    }

}
