package cz.kp.expensecalc.calculation.service.ws;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cz.kp.expensecalc.calculation.service.CalculationServiceImpl;

/**
 * Yahoo currency converter allows users to use the current exchange rate
 * between two national currencies to determine what one is worth in terms of
 * the other
 * 
 * @author dmarusca
 *
 */
public class YahooCurrencyConverterService implements CurrencyConverterService {

	public static final String URL = "http://quote.yahoo.com/d/quotes.csv?s={currencyFrom}{currencyTo}=X&f=l1&e=.csv";

	
	private static final Logger logger = Logger.getLogger(YahooCurrencyConverterService.class);

	/**
	 * 
	 */
	public BigDecimal convert(String currencyFrom, String currencyTo) throws IOException {
		if (currencyFrom.equals(currencyTo)) {
			return BigDecimal.ONE;
		}

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(URL, String.class, currencyFrom, currencyTo);

		return new BigDecimal(response.trim());
	}

	public static void main(String[] args) {
		YahooCurrencyConverterService ycc = new YahooCurrencyConverterService();
		try {
			System.out.println(ycc.convert("USD", "ILS"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
