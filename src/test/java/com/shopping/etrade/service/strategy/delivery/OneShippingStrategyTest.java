package com.shopping.etrade.service.strategy.delivery;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;

public class OneShippingStrategyTest {


	public OneShippingStrategy oneShippingStrategy;

	@Before
	public void setup() {
		oneShippingStrategy = new OneShippingStrategy();
	}
	@Test
	public void testCalculateDeliveryCost() throws IncompatibleCurrencyException {
		MoneyDTO shippingAmount = oneShippingStrategy.calculateDeliveryCost(1);
		assertThat(shippingAmount.getAmount()).isEqualTo(new BigDecimal(11));
		assertThat(shippingAmount.getCurrency()).isEqualTo(MoneyDTO.TL);
	}
}
