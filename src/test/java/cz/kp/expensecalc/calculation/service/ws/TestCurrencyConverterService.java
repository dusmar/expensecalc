package cz.kp.expensecalc.calculation.service.ws;

import java.io.IOException;
import java.math.BigDecimal;

import javax.transaction.NotSupportedException;

import org.springframework.web.client.RestTemplate;


public class TestCurrencyConverterService implements CurrencyConverterService {

	public BigDecimal convert(String currencyFrom, String currencyTo) throws IOException {
		if (currencyFrom.equals(currencyTo)) {
			return BigDecimal.ONE;
		}
		
		if (currencyTo.equals("EUR")) {
			return new BigDecimal(20);
		}
		

		if (currencyTo.equals("USD")) {
			return new BigDecimal(10);
		}
		
		throw new UnsupportedOperationException("unknows currency");
		
	}


}
