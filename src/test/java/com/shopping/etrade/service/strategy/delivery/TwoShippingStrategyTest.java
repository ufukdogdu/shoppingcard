package com.shopping.etrade.service.strategy.delivery;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;

public class TwoShippingStrategyTest {


	public TwoShippingStrategy twoShippingStrategy;

	@Before
	public void setup() {
		twoShippingStrategy = new TwoShippingStrategy();
	}
	@Test
	public void testCalculateDeliveryCost() throws IncompatibleCurrencyException {
		MoneyDTO shippingAmount = twoShippingStrategy.calculateDeliveryCost(1);
		assertThat(shippingAmount.getAmount()).isEqualTo(new BigDecimal(11));
		assertThat(shippingAmount.getCurrency()).isEqualTo(MoneyDTO.TL);
	}
}