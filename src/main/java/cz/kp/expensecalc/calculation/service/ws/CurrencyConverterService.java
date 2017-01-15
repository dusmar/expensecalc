package cz.kp.expensecalc.calculation.service.ws;

import java.math.BigDecimal;

public interface CurrencyConverterService {

    public BigDecimal convert(String currencyFrom, String currencyTo) throws Exception;
	
}
