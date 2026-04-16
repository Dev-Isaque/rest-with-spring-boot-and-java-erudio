package br.com.erudio.request.converters;

import java.math.BigDecimal;
import br.com.erudio.exception.UnsupportedMathOperationException;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty())
            return false;

        String number = strNumber.replace(",", ".");

        return number.matches("^[-+]?[0-9]+([.][0-9]+)?$");
    }

    public static Double roundResult(Double value) {
        if (value.isNaN() || value.isInfinite()) {
            return value;
        }

        BigDecimal bd = new BigDecimal(value.toString());
        bd = bd.setScale(2, java.math.RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}