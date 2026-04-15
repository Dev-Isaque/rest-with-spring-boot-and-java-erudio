package br.com.erudio;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsupportedMathOperationException;

@RestController
@RequestMapping("/math")
public class MathController {

    private Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        String number = strNumber.replace(",", ".");

        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty())
            return false;

        String number = strNumber.replace(",", ".");

        return number.matches("^[0-9]+([.][0-9]+)?$");
    }

    private Double roundResult(Double value) {
        BigDecimal bd = new BigDecimal(value.toString());
        bd = bd.setScale(2, java.math.RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // http://localhost:8080/math/sum/1/2
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/sub/1/2
    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/multiplication/1/2
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/division/1/2
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        if (convertToDouble(numberTwo) == 0)
            throw new UnsupportedMathOperationException(
                    "It's not possible to divide by 0. Please enter another number!");

        return convertToDouble(numberOne) / convertToDouble(numberTwo);

    }

    // http://localhost:8080/math/mean/1/2
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    // http://localhost:8080/math/square-root/1/2
    @RequestMapping("square-root/{number}")
    public Double squareRoot(
            @PathVariable("number") String number) {

        if (!isNumeric(number))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        Double result = roundResult(Math.sqrt(convertToDouble(number)));

        return result;
    }
}
