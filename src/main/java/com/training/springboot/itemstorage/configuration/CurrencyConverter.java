package com.training.springboot.itemstorage.configuration;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "currency")
public class CurrencyConverter {

	public Map<String, Currency> converter;

	public Optional<Currency> getCurrency(String market) {
		if (market == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(converter.get(market));
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Currency {

		private String coin;
		private BigDecimal rate;

	}


}

