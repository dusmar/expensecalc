package cz.kp.expensecalc.calculation.service.ws;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YahooCurrencyConverterService implements CurrencyConverterService {

	public BigDecimal convert(String currencyFrom, String currencyTo) throws IOException {
		if (currencyFrom.equals(currencyTo)) {
			return BigDecimal.ONE;
		}
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(
				"http://quote.yahoo.com/d/quotes.csv?s={currencyFrom}{currencyTo}=X&f=l1&e=.csv", String.class,
				currencyFrom, currencyTo);
		
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
