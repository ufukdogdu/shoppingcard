package com.shopping.etrade.service.strategy.delivery;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;

public class MoreThanTwoShippingStrategyTest {


	public MoreThanTwoShippingStrategy moreThanTwoShippingStrategy;

	@Before
	public void setup() {
		moreThanTwoShippingStrategy = new MoreThanTwoShippingStrategy();
	}
	@Test
	public void testCalculateDeliveryCost() throws IncompatibleCurrencyException {
		MoneyDTO shippingAmount = moreThanTwoShippingStrategy.calculateDeliveryCost(1);
		assertThat(shippingAmount.getAmount()).isEqualTo(new BigDecimal(10));
		assertThat(shippingAmount.getCurrency()).isEqualTo(MoneyDTO.TL);
	}
}